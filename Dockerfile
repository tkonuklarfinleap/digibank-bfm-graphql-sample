FROM openjdk:10-jre

EXPOSE 8080

CMD ["./bin/docker-entrypoint.sh"]

RUN mkdir -p config/docker /infinitec/bin
WORKDIR /infinitec

COPY ./config/docker /infinitec/config/docker
COPY ./bin/docker-entrypoint.sh /infinitec/bin/
COPY ./build/libs/infinitec.jar /infinitec/
