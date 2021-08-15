import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.21"
    id("org.jmailen.kotlinter") version "3.3.0"
}

tasks.jar {
    manifest {
        attributes (mapOf(Pair("Main-Class", "clarify.app.kt")))
    }
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val junitVersion: String = "5.7.2"
val http4kVersion: String = "3.253.0"

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.http4k:http4k-server-jetty:$http4kVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("com.natpryce:hamkrest:1.8.0.1")
    testImplementation("org.http4k:http4k-client-okhttp:$http4kVersion")
    testImplementation("org.http4k:http4k-testing-hamkrest:$http4kVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

tasks {
    withType<KotlinCompile>{
        kotlinOptions {
            jvmTarget = "11"
        }
    }
    withType<Test> {
        useJUnitPlatform()
        testLogging {
            showExceptions = true
            exceptionFormat = TestExceptionFormat.FULL
            events("passed", "skipped", "failed")
        }
    }
}
