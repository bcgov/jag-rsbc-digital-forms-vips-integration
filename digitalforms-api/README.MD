## Digital Forms API  

This is the API level of this of this project. 

### Running the application locally

* Most easily imported to and run using Eclipse or STS4 

* Import as a Maven project from the root level (this level). Dependent API should 
load automatically. 

* Set environmental variables 

run:   
```
mvn clean install  
mvn spring-boot:run  
```  

* In STS4, launch the **digitalforms-api** from the Boot Dashboard.     

### Security

* Basic Auth has been applied to all API operations. 
* Swagger2 and Actuator endpoints are not protected.
* Swagger2 may be disabled using the API properties file. 
* VIPS ORDS Client uses basic auth.        

### Swagger2

http://localhost:8082/digitalforms/v2/api-docs  
http://localhost:8082/digitalforms/swagger-ui.html  

* Note: Swagger2 may be disabled using the API properties file (See Environmental Variables below) 

### Actuator

To monitor and manage the application

|  URL |  Method |
|----------|--------------|
|`http://localhost:8082/digitalforms/actuator/`             | GET |
|`http://localhost:8082/digitalforms/actuator/health`        | GET |
|`http://localhost:8082/digitalforms/actuator/info`          | GET |


### Environmental Variables

These values must be set either as OpenShift secrets or environmental variables if running 
locally the API locally (As run configuration in STS4 or Eclipse)

|  Name |  Example Value |
|----------|--------------|
| DIGITALFORMS_BASICAUTH_PASSWORD | myPassword |
| DIGITALFORMS_BASICAUTH_USER | myUser |
| DIGITALFORMS_SWAGGER_ENABLED  | true |
| DIGITALFORMS_VIPSORDS_BASEPATH | my base path |
| DIGITALFORMS_VIPSORDS_USER | myUser |
| DIGITALFORMS_VIPSORDS_PASSWORD  | myPassword |


### Files and Directories

TBD

### API Operations categories. 

|  Category |  Summary |
|----------|--------------|
|`utility`| Utility operations |
|`forms`   | Forms related operations |
|`queries`   | IRP Query operations |
|`other`   | Other yet undefined operation type |

* Note: Please see Swagger2 endpoints for a complete breakdown of API Operations and 
  parameters

### License

Apache license 2.0


