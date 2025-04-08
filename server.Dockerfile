FROM openjdk:23-slim
#COPY --chown=gradle:gradle . /home/gradle/
WORKDIR /home/gradle

#RUN apk add --no-cache binutils

# Create our own JRE to lower space usage
# The --add-modules line tells jlink what to include, modify if other stuff is needed
# https://blog.wolt.com/engineering/2022/05/13/how-to-reduce-jvm-docker-image-size/
#RUN $JAVA_HOME/bin/jlink \
#         --verbose \
#         --add-modules java.base,java.desktop,java.instrument,java.management,java.naming,java.security.sasl,jdk.crypto.ec,java.sql,jdk.unsupported \
#         --strip-debug \
#         --no-man-pages \
#         --no-header-files \
#         --compress=2 \
#         --output /hdjre

# Build our jar file
#RUN ./gradlew :server:shadowJar --no-daemon
COPY --chmod=0777 --chown=gradle "server/build/libs/server-1.0.0-all.jar" /home/gradle/server.jar

ENTRYPOINT ["java","-jar","/home/gradle/server.jar"]