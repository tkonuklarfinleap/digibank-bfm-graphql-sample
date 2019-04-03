# Nova project template

This project is a template which should be used as a core to create a new project within Nova platform.

## Libraries&Tools
* Java 11
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

## Health check

The service provides health check at `GET /internal/health`. 
[See here](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-endpoints.html#production-ready-health) for more information. 
Example response is as follows:

```json
{
  "status": "UP",
  "details": {
    "diskSpace": {
      "status": "UP",
      "details": {
        "total": 502468108288,
        "free": 422851829760,
        "threshold": 10485760
      }
    },
    "binders": {
      "status": "UP",
      "details": {
        "kafka": {
          "status": "UP",
          "details": {
            "kafkaBinderHealthIndicator": {
              "status": "UP"
            }
          }
        }
      }
    },
    "refreshScope": {
      "status": "UP"
    },
    "discoveryComposite": {
      "status": "UP",
      "details": {
        "discoveryClient": {
          "status": "UP",
          "details": {
            "services": [
              "core-inbox",
              "os-request-routing",
              "partner-api-gateway",
              "microproduct-bpem",
              "os-audit-trail",
              "exact-integration-module",
              "core-communication",
              "microproduct-invoices",
              "core-ftl",
              "business-api-gateway",
              "os-request-tracing",
              "os-user-authenticator"
            ]
          }
        },
        "eureka": {
          "description": "Eureka discovery client has not yet successfully connected to a Eureka server",
          "status": "UP",
          "details": {
            "applications": {
              "CORE-INBOX": 1,
              "MICROPRODUCT-BPEM": 1,
              "OS-AUDIT-TRAIL": 1,
              "OS-REQUEST-ROUTING": 1,
              "EXACT-INTEGRATION-MODULE": 1,
              "CORE-COMMUNICATION": 1,
              "PARTNER-API-GATEWAY": 1,
              "MICROPRODUCT-INVOICES": 1,
              "CORE-FTL": 1,
              "BUSINESS-API-GATEWAY": 1,
              "OS-REQUEST-TRACING": 1,
              "OS-USER-AUTHENTICATOR": 1
            }
          }
        }
      }
    },
    "clientConfigServer": {
      "status": "UP",
      "details": {
        "propertySources": [
          "file:config/application.yml"
        ]
      }
    },
    "hystrix": {
      "status": "UP"
    }
  }
}
```
