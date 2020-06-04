## Digital Forms API

This is the root level of this project. 

### Built With

*   [Maven](https://maven.apache.org/) - Dependency Management
*   [JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Java™ Platform, Standard Edition Development Kit 
*   [Spring Boot](https://spring.io/projects/spring-boot) - Framework to ease the bootstrapping and development of new Spring Applications
*   [git](https://git-scm.com/) - Free and Open-Source distributed version control system 
*   [Swagger](https://swagger.io/) - Open-Source software framework backed by a large ecosystem of tools that helps developers design, build, document, and consume RESTful Web services.

### External Tools Used

* [Postman](https://www.getpostman.com/) - API Development Environment (Testing Docmentation)

### Running the application locally

* Most easily imported to and run using Eclipse or STS4 

* Import as a Maven project from the root level (this level). Dependent API should 
load automatically. 

* Set environmental variables  

run:   
```
mvn clean install  
mvn package -P all  
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

### Actuator

To monitor and manage the application

|  URL |  Method |
|----------|--------------|
|`http://localhost:8082/digitalforms/actuator/`             | GET |
|`http://localhost:8082/digitalforms/actuator/health`        | GET |
|`http://localhost:8082/digitalforms/actuator/info`          | GET |

### Files and Directories

TBD

### License

Apache license 2.0
