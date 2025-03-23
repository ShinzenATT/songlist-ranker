FROM gradle:8-jdk21-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/
WORKDIR /home/gradle

RUN gradle :composeApp:wasmJsProductionExecutableCompileSync --no-daemon

FROM nginx:alpine

COPY --from=build /home/gradle/build/js/packages/composeApp/kotlin /usr/share/nginx/html