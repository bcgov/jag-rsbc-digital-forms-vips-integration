# Bootstrap
Setup openshift tools
## Network policies
If Aporato is enabled then apply network policies from
https://github.com/BCDevOps/platform-services/tree/master/security/aporeto/docs
```shell script
export NAMESPACE_PREFIX=
export NAMESPACE_SUFFIX=
export TARGET_NAMESPACE=${NAMESPACE_PREFIX}-${NAMESPACE_SUFFIX}
export TOOLS_NAMESPACE=${NAMESPACE_PREFIX}-tools
export GIT_REPO="BCDevOps/platform-services"
export GIT_BRANCH="master"
export GIT_URL="https://raw.githubusercontent.com/${GIT_REPO}/${GIT_BRANCH}"

oc process -o=yaml \
  -f ${GIT_URL}/security/aporeto/docs/sample/quickstart-nsp.yaml \
  -p namespace=${TARGET_NAMESPACE} \
  | oc apply -f - -n ${TOOLS_NAMESPACE}
```
## Jenkins
### Jenkins master
```shell script
export NAMESPACE_PREFIX=
export TOOLS_NAMESPACE=${NAMESPACE_PREFIX}-tools
export GIT_REPO="bcgov/jag-rsbc-digital-forms-vips-integration"
export GIT_BRANCH="master"
export GIT_URL="https://raw.githubusercontent.com/${GIT_REPO}/${GIT_BRANCH}"

oc process -o=yaml \
  -f ${GIT_URL}/openshift/templates/jenkins-pipeline.yaml \
  -p -p MEMORY_LIMIT=6Gi \
  -p CPU_LIMIT=4 \
  | oc apply -f - -n ${TOOLS_NAMESPACE}
```
### maven slave
May not be required if version of java/maven in default jenkins maven slave is same or higher
```shell script
export NAMESPACE_PREFIX=
export TOOLS_NAMESPACE=${NAMESPACE_PREFIX}-tools
export GIT_REPO="bcgov/jag-rsbc-digital-forms-vips-integration"
export GIT_BRANCH="master"
export GIT_URL="https://raw.githubusercontent.com/${GIT_REPO}/${GIT_BRANCH}"

oc process -o=yaml \
  -f ${GIT_URL}/openshift/templates/jenkins-slave-maven.yaml \
  -p namespacePrefix=${NAMESPACE_PREFIX}  \
  | oc apply -f - -n ${TOOLS_NAMESPACE}
```
### zap slave
```shell script
export NAMESPACE_PREFIX=
export TOOLS_NAMESPACE=${NAMESPACE_PREFIX}-tools
export GIT_REPO="bcgov/jag-rsbc-digital-forms-vips-integration"
export GIT_BRANCH="master"
export GIT_URL="https://raw.githubusercontent.com/${GIT_REPO}/${GIT_BRANCH}"

oc process -o=yaml \
  -f ${GIT_URL}/openshift/templates/jenkins-slave-zap.yaml \
  -p namespacePrefix=${NAMESPACE_PREFIX}  \
  | oc apply -f - -n ${TOOLS_NAMESPACE}
```
## Sonarqube
```shell script
export NAMESPACE_PREFIX=
export TOOLS_NAMESPACE=${NAMESPACE_PREFIX}-tools
export GIT_REPO="bcgov/jag-rsbc-digital-forms-vips-integration"
export GIT_BRANCH="master"
export GIT_URL="https://raw.githubusercontent.com/${GIT_REPO}/${GIT_BRANCH}"

oc process -o=yaml \
  -f ${GIT_URL}/openshift/templates/sonarqube-postgresql.yaml \
  | oc apply -f - -n ${TOOLS_NAMESPACE}
```
**NOTE:** Plugins are lost when permanent storage is mounted on plugins directory. To reinstall plugins:
```shell script
cd /opt/sonarqube/extensions/plugins
# ZAP
curl -sSL -o sonar-zap-plugin-1.2.0.jar https://github.com/Coveros/zap-sonar-plugin/releases/download/sonar-zap-plugin-1.2.0/sonar-zap-plugin-1.2.0.jar
```
Change the admin password. Take password from .../resources/secrets
# Deploy Applications
## DigitalForms-API
### Deployment Pipeline
```shell script
export NAMESPACE_PREFIX=
export NAMESPACE_SUFFIX=
export TARGET_NAMESPACE=${NAMESPACE_PREFIX}-${NAMESPACE_SUFFIX}
export TOOLS_NAMESPACE=${NAMESPACE_PREFIX}-tools
export GIT_REPO="bcgov/jag-rsbc-digital-forms-vips-integration"
export GIT_BRANCH="master"
export GIT_URL="https://raw.githubusercontent.com/${GIT_REPO}/${GIT_BRANCH}"

# Configuration evn/secrets
oc process -o=yaml \
  -f ${GIT_URL}/openshift/templates/resources/secrets/digitalforms-api.yaml \
  -p DIGITALFORMS_BASICAUTH_PASSWORD=  \
  -p DIGITALFORMS_BASICAUTH_USER= \
  -p DIGITALFORMS_SWAGGER_ENABLED= \
  -p DIGITALFORMS_VIPSORDS_BASEPATH= \
  -p DIGITALFORMS_VIPSORDS_USER= \
  -p DIGITALFORMS_VIPSORDS_PASSWORD= \
  -p SPRING_PROFILES_ACTIVE= \
  | oc apply -f - -n ${TARGET_NAMESPACE}

oc process -o=yaml \
  -f ${GIT_URL}/openshift/templates/resources/secrets/splunk-config.yaml \
  -p URL=  \
  -p TOKEN=  \
  | oc apply -f - -n ${TARGET_NAMESPACE}

# Image stream
oc process -o=yaml \
  -f ${GIT_URL}/openshift/templates/builds/images/generic.yaml \
  -p namespacePrefix=${NAMESPACE_PREFIX}  \
  -p appName="digitalforms-api"  \
  | oc apply -f - -n ${TOOLS_NAMESPACE}

oc process -o=yaml \
  -f ${GIT_URL}/openshift/templates/builds/images/java.yaml \
  -p namespacePrefix=${NAMESPACE_PREFIX}  \
  | oc apply -f - -n ${TOOLS_NAMESPACE}

# Build config
oc process -o=yaml \
  -f ${GIT_URL}/openshift/templates/builds/builds/digitalforms-api.yaml \
  -p namespacePrefix=${NAMESPACE_PREFIX}  \
  | oc apply -f - -n ${TOOLS_NAMESPACE}

# Pipeline
oc process -o=yaml \
  -f ${GIT_URL}/openshift/templates/builds/pipelines/digitalforms-api.yaml \
  -p namespacePrefix=${NAMESPACE_PREFIX}  \
  | oc apply -f - -n ${TOOLS_NAMESPACE}
```
Change route spec.host url to private/public as per environment

|  Scope |  Url |
|----------|----------------------|
| Public | *.pathfinder.gov.bc.ca |
| Private | *.pathfinder.bcgov |

### Scans Pipeline
```shell script
export NAMESPACE_PREFIX=
export NAMESPACE_SUFFIX=
export TARGET_NAMESPACE=${NAMESPACE_PREFIX}-${NAMESPACE_SUFFIX}
export TOOLS_NAMESPACE=${NAMESPACE_PREFIX}-tools
export GIT_REPO="bcgov/jag-rsbc-digital-forms-vips-integration"
export GIT_BRANCH="master"
export GIT_URL="https://raw.githubusercontent.com/${GIT_REPO}/${GIT_BRANCH}"

# Pipeline
oc process -o=yaml \
  -f ${GIT_URL}/openshift/templates/builds/pipelines/digitalforms-api-scans.yaml \
  -p namespacePrefix=${NAMESPACE_PREFIX}  \
  -p apiDefinition=  \
  -p sonartoken=  \
  | oc apply -f - -n ${TOOLS_NAMESPACE}
```
