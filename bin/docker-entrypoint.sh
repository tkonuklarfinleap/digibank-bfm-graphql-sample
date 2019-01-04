#!/usr/bin/env bash
set -o xtrace
set -o errexit

# Two properties are used to set logging configuration:
# 1. log4j.config - used by 3rd party libs
# 2. logging.config - used by Spring Boot

# When run in docker container (e.g. ECS) we need to provide Eureka with IP and port as seen on docker host.
# When run on ECS, ECS_CONTAINER_METADATA_FILE env variable points to a file with container metadata.
# We use it to fetch the port as seen on docker host. IP is taken from EC2 metadata endpoint.
if [[ ! -z $ECS_CONTAINER_METADATA_FILE ]]; then
  export ECS_INSTANCE_IP_ADDRESS=$(curl --retry 3 --connect-timeout 3 -s 169.254.169.254/latest/meta-data/local-ipv4)
  export ECS_TASK_NON_SECURE_INSTANCE_PORT=$(cat ${ECS_CONTAINER_METADATA_FILE} | grep HostPort | awk -F ": " '{print $2}' | sed 's/,$//g')
  echo "ECS_INSTANCE_IP_ADDRESS           = $ECS_INSTANCE_IP_ADDRESS"
  echo "ECS_TASK_NON_SECURE_INSTANCE_PORT = $ECS_TASK_NON_SECURE_INSTANCE_PORT"

  echo "--- ECS container metadata ---"
  cat $ECS_CONTAINER_METADATA_FILE
  echo "--- End of ECS container metadata ---"
fi

java $JAVA_OPTS $APP_OPTS \
  -Djava.security.egd=file:/dev/./urandom \
  -Dlogging.config=./config/docker/logback.xml \
  -jar application.jar

# One can pass extra commands that can be passed for troubleshooting in case the application terminates immediately
# after startup
exec "$@"
