# Nova project template

This project is a template which should be used as a core to create a new project within Nova platform.

## Libraries&Tools
* Java 10
* Gradle
* Spring Boot 2
  - Actuator
  - Web
  - Test
* Vavr
* Immutables
* Lombok
* Junit 5
* AssertJ
* Mockito
* RestAssured
* Checkstyle

## How to import project in Intellij IDEA
#### Open project in Intellij IDEA
Open -> choose project directory -> Open

Check options:

* Use auto-import

* Create separate module per source set

* Use default gradle wrapper

#### Needed plugins

* Lombok 

#### Set annotation processing
Open Settings/Preferences -> Build, Execution, Deployment -> Compiler -> Annotation Processors

Check options:

* Enable annotation processing

* Obtain processors from project class path

* Module content root

Set source directories paths to:

* Production sources directory: ```generated```

* Test sources directory: ```generated_tests```

#### Enable Lombok
1. Open Settings/Preferences -> Other settings -> Lombok plugin
2. Check option: Enable Lombok plugin for this project

# Docker 

The project contains a few docker-compose files:

docker-compose.yml - that runs the application with all related services .e.g database
docker-compose-integration-test.yml - that runs database needed by integration tests

## How to run 

Move to project root directory and execute ```docker-compose up``` 
