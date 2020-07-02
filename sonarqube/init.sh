#!/bin/bash

# Wait for sonarqube to startup
while [ "$(curl -sS -o /dev/null -w "%{http_code}" "${SONAR_URL}")" != "200" ]
do
  echo "Waiting for sonarqube ..."
  sleep 3
done

echo "Waiting for all sonarqube services to be up ..."
tail -f /opt/logs/sonar.log | grep -q "SonarQube is up"

# Set admin password
# Check if admin password is set
echo "Checking admin password ..."
if curl -ksS --user admin:"${SONAR_PASSWORD}" "${SONAR_URL}"/api/authentication/validate | jq '.valid' | grep -q "true"
then
  echo "Admin password exists"
else
  echo "Setting admin password ..."
  if curl -ksS --user admin:admin "${SONAR_URL}"/api/authentication/validate | jq '.valid' | grep -q "true"
  then
    echo "Setting admin password using API ..."
    curl -X POST -ksS --user admin:admin "${SONAR_URL}"/api/users/change_password --data-urlencode "login=admin" --data-urlencode "password=${SONAR_PASSWORD}" --data-urlencode "previousPassword=admin"
  else
    echo "Setting admin password using DB/API ..."
    # Reset password to admin:admin
    psql -h postgresql -U sonar -w -d sonar -c "update users set crypted_password = '\$2a\$12\$uCkkXmhW5ThVK8mpBvnXOOJRLd64LJeHTeCkSuB3lfaR2N0AYBaSi', salt=null, hash_method='BCRYPT' where login = 'admin'"
    # Set password to one from .env
    curl -X POST -ksS --user admin:admin "${SONAR_URL}"/api/users/change_password --data-urlencode "login=admin" --data-urlencode "password=${SONAR_PASSWORD}" --data-urlencode "previousPassword=admin"
  fi
fi

# Create project
# Check if project already exists
echo "Checking project exists ..."
if curl -ksS --user admin:"${SONAR_PASSWORD}" "${SONAR_URL}"/api/projects/search | jq '.components[]?.key' | grep -q "${SONAR_PROJECT_KEY}"
then
  echo "Project exists"
else
  echo "Creating Project ..."
  curl -X POST -ksS --user admin:"${SONAR_PASSWORD}" "${SONAR_URL}"/api/projects/create --data-urlencode "name=Digital Forms API" --data-urlencode "project=${SONAR_PROJECT_KEY}"
fi

# Create user token
# Check if user token exists
echo "Checking token exists ..."
if curl -ksS --user admin:"${SONAR_PASSWORD}" "${SONAR_URL}"/api/user_tokens/search | jq '.userTokens[]?.name' | grep -q "digitalforms_api_token"
then
  echo "Token exists"
else
  echo "Creating token ..."
  curl -X POST -ksS --user admin:"${SONAR_PASSWORD}" "${SONAR_URL}"/api/user_tokens/generate --data-urlencode "name=digitalforms_api_token"
fi

# Update database with predefined user token
# Test new token
echo "Testing token validity ..."
if curl -ksS --user "${SONAR_TOKEN}": "${SONAR_URL}"/api/authentication/validate | jq '.valid' | grep -q "true"
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
  curl -X POST -ksS --user admin:"${SONAR_PASSWORD}" "${SONAR_URL}"/api/system/restart
fi
