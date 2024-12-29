plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.springBoot)
    alias(libs.plugins.springDependencyManagement)
    alias(libs.plugins.springDoc)
    alias(libs.plugins.asciidoctor)
    alias(libs.plugins.kotlinxSerialization)
    application
}

group = "se.chalmers.hd"
version = "1.0.0"
application {
    mainClass.set("se.chalmers.hd.ApplicationKt")
}

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(23)
    }
}

dependencies {
    implementation(projects.shared)
    implementation(libs.spring.boot.starter.actuator)
    implementation(libs.spring.boot.starter.quartz)
    implementation(libs.spring.boot.starter.webflux)
    implementation(libs.spring.boot.starter.websocket)
    implementation(libs.jackson.module.kotlin)
    implementation(libs.reactor.kotlin.extensions)
    implementation(libs.kotlin.reflect)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.reactor)
    implementation(libs.spring.session.core)
    implementation(libs.kotlinx.serialization)

    implementation(libs.exposed.core)
    implementation(libs.exposed.dao)
    implementation(libs.exposed.jdbc)
    implementation(libs.exposed.datetime)
    implementation(libs.exposed.spring.boot)
    implementation(libs.postgresql)

    testImplementation(libs.kotlin.test.junit)
    testImplementation(libs.reactor.test)
    testImplementation(libs.spring.restdocs.webtestclient)
    testRuntimeOnly(libs.junit.platform.launcher)
    testRuntimeOnly(libs.h2)

}