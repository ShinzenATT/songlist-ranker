FROM openjdk:23-slim
WORKDIR /home/gradle

# Build our jar file
COPY --chmod=0777 --chown=gradle "server/build/libs/server-1.0.0-all.jar" /home/gradle/server.jar
COPY --chmod=0777 --chown=gradle server/build/resources/main/* /home/gradle/

HEALTHCHECK CMD "curl --fail --silent localhost:8080/actuator/health | grep UP || exit 1"

ENV SPRING_CONFIG_LOCATION=./application.yml

ENTRYPOINT ["java","-jar","/home/gradle/server.jar"]