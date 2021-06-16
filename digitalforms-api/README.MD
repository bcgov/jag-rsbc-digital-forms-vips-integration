# Digital Forms API

This is the API level of this of this project.

# Technical Overview

Layer              | Technology
------------------ | ---------------------
Service            | Java, SpringFramework
Application Server | Spring Boot / Tomcat
Runtime            | Silver OpenShift

## Environmental Variables

These values must be set as environmental variables if running the API locally (As run configuration in STS4 or Eclipse)

Name                            | Example Value
------------------------------- | ----------------
DIGITALFORMS_BASICAUTH_PASSWORD | myPassword
DIGITALFORMS_BASICAUTH_USER     | myUser
DIGITALFORMS_SWAGGER_ENABLED    | true
DIGITALFORMS_VIPSORDS_BASEPATH  | my base path
DIGITALFORMS_VIPSORDS_USER      | myUser
DIGITALFORMS_VIPSORDS_PASSWORD  | myPassword
DIGITALFORMS_ORDS_BASEPATH      | my base path
DIGITALFORMS_ORDS_USER          | myUser
DIGITALFORMS_ORDS_PASSWORD      | myPassword
DIGITALFORMS_ORDS_USER_GUID     | myUser
SPLUNK_PASSWORD                 | Splunk password
SPLUNK_TOKEN                    | Splunk HEC Token
SPLUNK_URL                      | Splunk URL
SPRING_PROFILES_ACTIVE          | splunk

## Running the application locally

Refer to **Running the application** section of <https://github.com/bcgov/jag-rsbc-digital-forms-vips-integration/blob/master/README.md>

## Security

Refer to **Security** section of <https://github.com/bcgov/jag-rsbc-digital-forms-vips-integration/blob/master/README.md>

## Swagger2

Refer to **Swagger2** section of <https://github.com/bcgov/jag-rsbc-digital-forms-vips-integration/blob/master/README.md>

## Actuator

Refer to **Actuator** section of <https://github.com/bcgov/jag-rsbc-digital-forms-vips-integration/blob/master/README.md>

## Files and Directories

```
digitalforms-api
    └── src/../
     └── digitalforms
        ├── config     # Contains API Configuration files
        ├── controller     # Contains API controller files
        ├── exception     # Contains API Exception files
        ├── model     # Contains API Model files
        ├── security     # Contains API Security files
        ├── service     # Contains API Service files
        ├── swagger2     # Contains API Swagger files
        └── util     # Contains API Util files
```

## API Operations categories.

Category            | Summary
------------------- | ------------------------------------
`utility`           | Utility operations
`application form`  | Forms related operations
`query`             | Query operations
`payment`           | Payment related operations
`review scheduling` | Review Scheduling related operations
`disclosure`        | Disclosure related operations

- Note: Please see Swagger2 endpoints for a complete breakdown of API Operations and parameters

# DevOps Process

## Jenkins

Jenkins pipeline is triggered by merge request in the repository.

## DEV builds

Dev builds are triggered by merge request in the repository.

# Promotion to TEST

Login to the OpenShift Web Console and navigate to the Tools project for the system. Go to Builds->Pipelines. Click "Yes" on "Deploy to Test Approval" prompt.

# Promotion to PROD

Login to the OpenShift Web Console and navigate to the Tools project for the system. Go to Builds->Pipelines. Click "Yes" on "Deploy to Prod Approval" prompt.

## License

Apache license 2.0
