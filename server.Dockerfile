FROM gradle:8-jdk21-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/
WORKDIR /home/gradle

RUN apk add --no-cache binutils

# Create our own JRE to lower space usage
# The --add-modules line tells jlink what to include, modify if other stuff is needed
# https://blog.wolt.com/engineering/2022/05/13/how-to-reduce-jvm-docker-image-size/
RUN $JAVA_HOME/bin/jlink \
         --verbose \
         --add-modules java.base,java.desktop,java.instrument,java.management,java.naming,java.security.sasl,jdk.crypto.ec,java.sql,jdk.unsupported \
         --strip-debug \
         --no-man-pages \
         --no-header-files \
         --compress=2 \
         --output /hdjre

# Build our jar file
RUN ./gradlew :server:bootJar --no-daemon

FROM alpine:latest
ENV JAVA_HOME=/jre
ENV PATH="${JAVA_HOME}/bin:${PATH}"

# Copy out JRE from the base image
COPY --from=build /hdjre $JAVA_HOME

# Install webp for image conversion
RUN apk add --update-cache \
    ca-certificates \
    && rm -rf /var/cache/apk/*

EXPOSE 8080

COPY --from=build /home/gradle/src/build/libs/*-all.jar /home/web/hd_api.jar
WORKDIR /home/web
ENTRYPOINT ["/jre/bin/java","-jar","/home/web/hd_api.jar"]