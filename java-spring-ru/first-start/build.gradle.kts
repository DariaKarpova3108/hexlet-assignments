import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    // BEGIN
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
    // END
    application
    id("com.github.ben-manes.versions") version "0.48.0"
}

group = "exercise"

version = "1.0-SNAPSHOT"

application { mainClass.set("exercise.Application") }

repositories {
    mavenCentral()
}

dependencies {
    // BEGIN
    implementation("org.springframework.boot:spring-boot-starter-web:3.3.2")
    implementation("org.springframework.boot:spring-boot-starter:3.3.2")
    implementation("org.springframework.boot:spring-boot-devtools:3.3.2")


    testImplementation("org.springframework.boot:spring-boot-starter-test:3.3.2")
    // END
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testImplementation("org.hamcrest:hamcrest:2.2")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
        events = mutableSetOf(TestLogEvent.FAILED, TestLogEvent.PASSED, TestLogEvent.SKIPPED)
        showStandardStreams = true
    }
}
