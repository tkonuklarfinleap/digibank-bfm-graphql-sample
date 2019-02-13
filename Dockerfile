FROM openjdk:11-jre

# curl is used in docker-entrypoint.sh to retrieve AWS meta-data
RUN apt-get update && apt-get install -y curl

EXPOSE 8080

CMD ["./bin/docker-entrypoint.sh"]

RUN mkdir -p config/docker /infinitec/bin
WORKDIR /infinitec

COPY ./config/docker /infinitec/config/docker
COPY ./bin/docker-entrypoint.sh /infinitec/bin/
COPY ./build/libs/application.jar /infinitec/
