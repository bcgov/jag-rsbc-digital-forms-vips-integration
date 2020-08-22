# Bootstrap
Setup openshift tools

**NOTE:**  Openshift pipeline is triggered using GitHub webhooks
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
Plugins to add:
* Jacoco
* Java Code Quality and Security
* ZAP
* Git

Change the admin password. Use password from .../resources/secrets
# Deploy Applications
## Static web server (for reports)
Need access to create service account and role binding in target namespace
```shell script
export NAMESPACE_PREFIX=
export NAMESPACE_SUFFIX=
export TARGET_NAMESPACE=${NAMESPACE_PREFIX}-${NAMESPACE_SUFFIX}
export GIT_REPO="bcgov/jag-rsbc-digital-forms-vips-integration"
export GIT_BRANCH="master"
export GIT_URL="https://raw.githubusercontent.com/${GIT_REPO}/${GIT_BRANCH}"

oc process -o=yaml \
  -f ${GIT_URL}/openshift/templates/static-web-server.dc.yaml \
  -p namespacePrefix=${NAMESPACE_PREFIX} \
  | oc apply -f - ${TARGET_NAMESPACE} -n ${TARGET_NAMESPACE}
```
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
## Create secrets synchronized with Jenkins
## Option 1:
### Pass base64 value of env file.
### enter values for variables in: ./test-automation/env.template
cat ./test-automation/env.template | sed -r "s/^([a-zA-Z]+)/export \1/g" | base64 | tr -d '\n'
### Copy the output and pass as argument for file
oc process -o=yaml \
  -f ${GIT_URL}/openshift/templates/resources/secrets/cucumber-maven-config.yaml \
  -p file=  \
  | oc apply -f - -n ${TOOLS_NAMESPACE}

## Option 2:
cat ./test-automation/env.template | sed -r "s/^([a-zA-Z]+)/export \1/g" > env.cucumber
oc create secret generic cucumber-maven-config --from-file=filename=env.cucumber
oc label secret cucumber-maven-config credential.sync.jenkins.openshift.io=true

oc process -o=yaml \
  -f ${GIT_URL}/openshift/templates/resources/secrets/digitalforms-api.yaml \
  -p DIGITALFORMS_BASICAUTH_PASSWORD=  \
  -p DIGITALFORMS_BASICAUTH_USER= \
  -p DIGITALFORMS_SWAGGER_ENABLED= \
  -p DIGITALFORMS_VIPSORDS_BASEPATH= \
  -p DIGITALFORMS_VIPSORDS_USER= \
  -p DIGITALFORMS_VIPSORDS_PASSWORD= \
  -p DIGITALFORMS_ORDS_USER_GUID= \
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
