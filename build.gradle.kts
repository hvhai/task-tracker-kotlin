plugins {
    kotlin("jvm") version "2.1.0"
    application
}

group = "com.codehunter.task_tracker_kotlin"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("task_tracker_kotlin.MainKt")
}
tasks.withType<JavaExec> {
    standardInput = System.`in`
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}