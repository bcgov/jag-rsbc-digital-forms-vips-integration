## Digital Forms API

This is the root level of this project.

### Built With

- [Maven](https://maven.apache.org/) - Dependency Management
- [JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Java Platform, Standard Edition Development Kit
- [Spring Boot](https://spring.io/projects/spring-boot) - Framework to ease the bootstrapping and development of new Spring Applications
- [git](https://git-scm.com/) - Free and Open-Source distributed version control system
- [Swagger](https://swagger.io/) - Open-Source software framework backed by a large ecosystem of tools that helps developers design, build, document, and consume RESTful Web services.

### External Tools Used

- [Postman](https://www.getpostman.com/) - API Development Environment (Testing Docmentation)
 
## Technical Overview

| Layer              | Technology                |
| ------------------ | ------------------------- |
| Service            | Java, SpringFramework     |
| Test framework     | TestNG, Cucumber, Gherkin |
| Application Server | Spring Boot / Tomcat      |
| Runtime            | Pathfinder OpenShift      |

### Building the application

1) After clone run ``` git submodule update --init```  

This will pull the last working versions of the shared client ORDS libraries; 
- jag-vips-client
- jag-digitalforms-client. 

2) Set the compiler version to java 8

3) Run this command to get code to a runnable state  
```mvn clean compile ```  

4) Run ```mvn clean package``` to package the application to a jar

5) To update the jag-vips-client submodule to the latest commit run  
```git pull```   
from the jag-vip-client directory or run  
```git submodule update --remote jag-vips-client```  

6) To update the jag-digitalforms-client submodule to the latest commit run  
```git pull```   
from the jag-digitalforms-client directory or run  
```git submodule update --remote jag-digitalforms-client``` 

7) Make sure to commit if you update the submodules



### Running the application

**1. Using Docker**

- Install [Docker](https://www.docker.com/)

- Create a .env file as defined in `.env.template`.

- Run in order (wait for stage to come up before starting the next):

```
docker-compose -f nexus-docker-compose.yml up --build
docker-compose up --build
```

**2. Using Eclipse or Spring Tool Suite 4 (STS4)**

- Import as a Maven project from the root level. Dependent API should load automatically.

- Set Spring boot environmental variables as defined in `.env.template`.

- Run `mvn clean install -Pdefault-profile`

- Launch the **digitalforms-api** from the Boot Dashboard.

**3. Using command line**

- Set environmental variables as defined in `.env.template`.

- Run in order:

```
mvn clean install -Pdefault-profile
mvn spring-boot:run
```

### Security

- Basic Authentication has been applied to all API operations.
- Swagger2 and Actuator endpoints are not protected.
- Swagger2 may be disabled using the API properties file.
- VIPS and DIGITALFORMS ORDS Clients both use basic authentication.

### Swagger2

http://localhost:8082/digitalforms/v3/api-docs  
http://localhost:8082/digitalforms/swagger-ui.html

### Actuator

To monitor and manage the application

| URL                                                 | Method |
| --------------------------------------------------- | ------ |
| `http://localhost:8082/digitalforms/actuator`       | GET    |
| `http://localhost:8082/digitalforms/actuator/health`| GET    |
| `http://localhost:8082/digitalforms/actuator/info`  | GET    |

### Files and Directories

```
.
├── .github                       # Contains GitHub Related sources
├── configuration                 # Contains Nexus configuration
├── digitalforms-api              # Digital forms API
├── jag-vips-client               # VIPS ORDS client library
├── jag-digitalforms-client       # DIGITAL FORMS ORDS client library
├── nexus                         # Contains Nexus setup files
├── openshift                     # Contains Openshift configuration templates
├── sonarqube                     # Contains SonarQube setup files
├── splunk                        # Contains Splunk setup files
├── test-automation               # Contains Digital forms automation tests
├── docker-compose.yml            # Digital forms API build file
└── nexus-docker-compose.yml      # Nexus build file
```

## Splunk

http://localhost:8000

| Key        | Value     |
| ---------- | --------- |
| `Username` | admin     |
| `Password` | <in .env> |

## Sonarqube

Linux/Unix Settings

```
sysctl -w vm.max_map_count=262144
sysctl -w fs.file-max=65536
ulimit -n 65536
ulimit -u 4096
```

http://localhost:9000

| Key        | Value     |
| ---------- | --------- |
| `Username` | admin     |
| `Password` | <in .env> |

## Authors

- Derek Heidorn
- Siva Karunakaran
- Shaun Millar
- Ebenezer Muthiah
- Rohit Sirohi

### License

Apache license 2.0
