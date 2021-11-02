## Digital Forms Automation tests

This is the Digital Forms API automation tests.

## Technical Overview

| Layer              | Technology                |
| ------------------ | ------------------------- |
| Test framework     | TestNG, Cucumber, Gherkin |
| Application Server | Spring Boot / Tomcat      |
| Runtime            | Pathfinder OpenShift      |

### Environmental Variables

These values must be set as environmental variables if running the API locally (As run configuration in STS4 or Eclipse)

| Name                    | Example Value |
| ----------------------- | ------------- |
| digitalFormsBaseUrl     | my base path  |
| digitalFormsApiUsername | myUser        |
| digitalFormsApiPassword | myPassword    |
| ordsApiUsername         | myUser        |
| ordsApiPassword         | myPassword    |
| automationEnv           | dev           |

### Running the tests

**1. Using Eclipse or Spring Tool Suite 4 (STS4)**

- Import `test-automation` as a Maven project.

- Set Spring boot environmental variables.

- Run `mvn clean compile test`

**2. Using command line**

- Set environmental variables.

- Run `mvn clean compile test`

### Test Reports

| Report           | Path                                                        |
| ---------------- | ----------------------------------------------------------- |
| `Extent Report`  | \*\*/test-automation/test-output/htmlReport/ExtentHtml.html |
| `Logger Reports` | \*\*/test-automation/test-output/loggerReport/\*            |

### Files and Directories

TBD

## DevOps Process

### Jenkins

Scans pipeline needs to be triggered manually.

- Login to the OpenShift Web Console and navigate to the Tools project for the system. Go to Builds->Pipelines.
- Start the Digital Forms API scans pipeline

### License

Apache license 2.0
