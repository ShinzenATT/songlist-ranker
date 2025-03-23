#FROM gradle:8-jdk21-alpine AS build
#COPY --chown=gradle:gradle . /home/gradle/
#WORKDIR /home/gradle

#RUN gradle :composeApp:wasmJsProductionExecutableCompileSync --no-daemon

FROM nginx:alpine as deploy

#COPY --from=build /home/gradle/build/js/packages/composeApp/kotlin /usr/share/nginx/html
COPY build/js/packages/composeApp/kotlin /usr/share/nginx/html