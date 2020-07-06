#!/bin/bash

# Wait for nexus3 to startup
while [ "$(curl -sS -o /dev/null -w "%{http_code}" "${NEXUS_URL}/service/rest/v1/status")" != "200" ]
do
  echo "Waiting for nexus ..."
  sleep 3
done

echo "Waiting for all nexus services to be up ..."
tail -f /nexus-data/log/nexus.log | grep -q "Started Sonatype Nexus OSS"

# Set admin password
# Check if admin password is set

echo "Checking admin password ..."
if [ "$(curl -sS -o /dev/null -w "%{http_code}" -u admin:"${NEXUS_ADMIN_PASSWORD}" -X GET "${NEXUS_URL}/service/rest/v1/status/check" -H  "accept: application/json")" = "200" ]
then
  echo "Admin password exists"
else
  echo "Setting admin password ..."
  if [ -f /nexus-data/admin.password ]
  then
    echo "Setting admin password using API ..."
    OLD_NEXUS_ADMIN_PASSWORD="$(cat /nexus-data/admin.password)"
    curl -u admin:"${OLD_NEXUS_ADMIN_PASSWORD}" -X PUT "${NEXUS_URL}/service/rest/beta/security/users/admin/change-password" -H  "accept: application/json" -H  "Content-Type: text/plain" -d "${NEXUS_ADMIN_PASSWORD}"
  else
    echo "Setting admin password using DB/API ..."
    echo "Follow instructions on https://support.sonatype.com/hc/en-us/articles/213467158-How-to-reset-a-forgotten-admin-password-in-Nexus-3-x "
    echo "Or delete storage volume and start over"
  fi
fi

# Disable anonymous access
echo "Disabling anonymous access ..."
curl -u admin:"${NEXUS_ADMIN_PASSWORD}" -X PUT "${NEXUS_URL}/service/rest/beta/security/anonymous" -H  "accept: application/json" -H  "Content-Type: application/json" -d "{  \"enabled\": false,  \"userId\": \"anonymous\",  \"realmName\": \"NexusAuthorizingRealm\"}"

#sleep 3600
#
#exit 0

# Create repositories
# Check if repositories already exists

# Hosted
REPO_ID="digitalforms-snapshots"
echo "Checking proxy repository $REPO_ID exists ..."
if curl -sS -u admin:"${NEXUS_ADMIN_PASSWORD}" -X GET "${NEXUS_URL}/service/rest/beta/repositories" -H  "accept: application/json" | jq '.[]?.name' | grep -q "${REPO_ID}"
then
  echo "Repository $REPO_ID exists"
else
  echo "Creating repository $REPO_ID..."
  curl -sS -u admin:"${NEXUS_ADMIN_PASSWORD}" -X POST "${NEXUS_URL}/service/rest/beta/repositories/maven/hosted" -H  "accept: application/json" -H  "Content-Type: application/json" -d "{  \"name\": \"digitalforms-snapshots\",  \"online\": true,  \"storage\": {    \"blobStoreName\": \"default\",    \"strictContentTypeValidation\": true,    \"writePolicy\": \"allow_once\"  },  \"cleanup\": {    \"policyNames\": [      \"string\"    ]  },  \"maven\": {    \"versionPolicy\": \"MIXED\",    \"layoutPolicy\": \"STRICT\"  }}"
fi

# Hosted
REPO_ID="digitalforms-releases"
echo "Checking proxy repository $REPO_ID exists ..."
if curl -sS -u admin:"${NEXUS_ADMIN_PASSWORD}" -X GET "${NEXUS_URL}/service/rest/beta/repositories" -H  "accept: application/json" | jq '.[]?.name' | grep -q "${REPO_ID}"
then
  echo "Repository $REPO_ID exists"
else
  echo "Creating repository $REPO_ID..."
  curl -sS -u admin:"${NEXUS_ADMIN_PASSWORD}" -X POST "${NEXUS_URL}/service/rest/beta/repositories/maven/hosted" -H  "accept: application/json" -H  "Content-Type: application/json" -d "{  \"name\": \"digitalforms-releases\",  \"online\": true,  \"storage\": {    \"blobStoreName\": \"default\",    \"strictContentTypeValidation\": true,    \"writePolicy\": \"allow_once\"  },  \"cleanup\": {    \"policyNames\": [      \"string\"    ]  },  \"maven\": {    \"versionPolicy\": \"MIXED\",    \"layoutPolicy\": \"STRICT\"  }}"
fi

# Proxy
REPO_ID="splunk-proxy"
echo "Checking proxy repository $REPO_ID exists ..."
if curl -sS -u admin:"${NEXUS_ADMIN_PASSWORD}" -X GET "${NEXUS_URL}/service/rest/beta/repositories" -H  "accept: application/json" | jq '.[]?.name' | grep -q "${REPO_ID}"
then
  echo "Repository $REPO_ID exists"
else
  echo "Creating repository $REPO_ID..."
  curl -sS -u admin:"${NEXUS_ADMIN_PASSWORD}" -X POST "${NEXUS_URL}/service/rest/beta/repositories/maven/proxy" -H  "accept: application/json" -H  "Content-Type: application/json" -d "{  \"name\": \"splunk-proxy\",  \"online\": true,  \"storage\": {    \"blobStoreName\": \"default\",    \"strictContentTypeValidation\": true  },  \"cleanup\": null,  \"proxy\": {    \"remoteUrl\": \"https://splunk.jfrog.io/artifactory/ext-releases-local/\",    \"contentMaxAge\": -1,    \"metadataMaxAge\": 1440  },  \"negativeCache\": {    \"enabled\": true,    \"timeToLive\": 1440  },  \"httpClient\": {    \"blocked\": false,    \"autoBlock\": false,    \"connection\": {      \"retries\": null,      \"userAgentSuffix\": null,      \"timeout\": null,      \"enableCircularRedirects\": false,      \"enableCookies\": false    },    \"authentication\": null  },  \"routingRule\": null,  \"maven\": {    \"versionPolicy\": \"MIXED\",    \"layoutPolicy\": \"STRICT\"  }}"
fi

# Proxy
REPO_ID="dps-group-proxy"
echo "Checking proxy repository $REPO_ID exists ..."
if curl -sS -u admin:"${NEXUS_ADMIN_PASSWORD}" -X GET "${NEXUS_URL}/service/rest/beta/repositories" -H  "accept: application/json" | jq '.[]?.name' | grep -q "${REPO_ID}"
then
  echo "Repository $REPO_ID exists"
else
  echo "Creating repository $REPO_ID..."
  curl -sS -u admin:"${NEXUS_ADMIN_PASSWORD}" -X POST "${NEXUS_URL}/service/rest/beta/repositories/maven/proxy" -H  "accept: application/json" -H  "Content-Type: application/json" -d "{  \"name\": \"dps-group-proxy\",  \"online\": true,  \"storage\": {    \"blobStoreName\": \"default\",    \"strictContentTypeValidation\": true  },  \"cleanup\": null,  \"proxy\": {    \"remoteUrl\": \"http://nexus-pjztlm-tools.pathfinder.gov.bc.ca/repository/dps-group/\",    \"contentMaxAge\": -1,    \"metadataMaxAge\": 1440  },  \"negativeCache\": {    \"enabled\": true,    \"timeToLive\": 1440  },  \"httpClient\": {    \"blocked\": false,    \"autoBlock\": false,    \"connection\": {      \"retries\": null,      \"userAgentSuffix\": null,      \"timeout\": null,      \"enableCircularRedirects\": false,      \"enableCookies\": false    },    \"authentication\": null  },  \"routingRule\": null,  \"maven\": {    \"versionPolicy\": \"MIXED\",    \"layoutPolicy\": \"STRICT\"  }}"
fi

# Group
REPO_ID="digitalforms-group"
echo "Checking proxy repository $REPO_ID exists ..."
if curl -sS -u admin:"${NEXUS_ADMIN_PASSWORD}" -X GET "${NEXUS_URL}/service/rest/beta/repositories" -H  "accept: application/json" | jq '.[]?.name' | grep -q "${REPO_ID}"
then
  echo "Repository $REPO_ID exists"
else
  echo "Creating repository $REPO_ID..."
  curl -sS -u admin:"${NEXUS_ADMIN_PASSWORD}" -X POST "${NEXUS_URL}/service/rest/beta/repositories/maven/group" -H  "accept: application/json" -H  "Content-Type: application/json" -d "{  \"name\": \"digitalforms-group\",  \"online\": true,  \"storage\": {    \"blobStoreName\": \"default\",    \"strictContentTypeValidation\": true  },  \"group\": {    \"memberNames\": [      \"maven-central\", \"digitalforms-snapshots\", \"digitalforms-releases\", \"splunk-proxy\", \"dps-group-proxy\"    ]  }}"
fi

