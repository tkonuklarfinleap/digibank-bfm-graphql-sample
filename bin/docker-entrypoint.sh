#!/usr/bin/env bash
set -o xtrace
set -o errexit

# Two properties are used to set logging configuration:
# 1. log4j.config - used by 3rd party libs
# 2. logging.config - used by Spring Boot

java $JAVA_OPTS $APP_OPTS \
  -Djava.security.egd=file:/dev/./urandom \
  -Dlogging.config=./config/docker/logback.xml \
  -jar infinitec.jar

# One can pass extra commands that can be passed for troubleshooting in case the application terminates immediately
# after startup
exec "$@"
