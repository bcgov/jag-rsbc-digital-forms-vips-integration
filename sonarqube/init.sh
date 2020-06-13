#!/bin/bash

# Wait for sonarqube to startup
while ! curl -kss --user admin:admin http://sonarqube:9000/api/system/health | jq ".health" | grep -q "GREEN"
do
  echo "Waiting for sonarqube ..."
  sleep 3
done

sleep 5

# Create project
# Check if project already exists
echo "Checking project exists ..."
if curl -ksS --user admin:admin http://sonarqube:9000/api/projects/search | jq '.components[]?.key' | grep -q "${SONAR_PROJECT_KEY}"
then
  echo "Project exists"
else
  echo "Creating Project ..."
  curl -X POST -ksS --user admin:admin http://sonarqube:9000/api/projects/create --data-urlencode "name=Digital Forms API" --data-urlencode "project=${SONAR_PROJECT_KEY}"
fi

# Create user token
# Check if user token exists
echo "Checking token exists ..."
if curl -ksS --user admin:admin http://sonarqube:9000/api/user_tokens/search | jq '.userTokens[]?.name' | grep -q "digitalforms_api_token"
then
  echo "Token exists"
else
  echo "Creating token ..."
  curl -X POST -ksS --user admin:admin http://sonarqube:9000/api/user_tokens/generate --data-urlencode "name=digitalforms_api_token"
fi

# Update database with predefined user token
# Test new token
echo "Testing token validity ..."
if curl -ksS --user "${SONAR_TOKEN}": http://sonarqube:9000/api/authentication/validate | jq '.valid' | grep -q "true"
then
  echo "Token is valid"
else
  echo "Updating token ..."
  TOKEN_HASH=$(echo -n "${SONAR_TOKEN}" | sha384sum | cut -d" " -f1 | tr -d "\n")
  psql -h postgresql -U sonar -w -d sonar -c "UPDATE user_tokens SET token_hash = '${TOKEN_HASH}' WHERE name = 'digitalforms_api_token'"
fi

# Add zap plugin
# Check if zap plugin exists
echo "Checking zap plugin exists ..."
if [ "$(sha256sum /opt/plugins/sonar-zap-plugin-1.2.0.jar | cut -f1 -d" " | tr -d "\n")" = "c17df6d825105f3160e67470cba42932b905b5eb488efd153fd9be7dadf34e43" ]
then
  echo "Zap plugin exists ..."
else
  echo "Adding zap plugin ..."
  curl -sSL -o /opt/plugins/sonar-zap-plugin-1.2.0.jar https://github.com/Coveros/zap-sonar-plugin/releases/download/sonar-zap-plugin-1.2.0/sonar-zap-plugin-1.2.0.jar

  # Restart Sonarqube after adding plugin
  echo "Restarting sonarqube ..."
  curl -X POST -ksS --user admin:admin http://sonarqube:9000/api/system/restart
fi
