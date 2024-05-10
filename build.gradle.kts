plugins {
    kotlin("jvm") version "1.9.22"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    //DCS: Base de datos H2
    implementation("com.h2database:h2:2.2.224")

//DCS: HikariCP
    implementation ("com.zaxxer:HikariCP:5.0.0")

//DCS: Arregla el warning SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
    implementation("org.slf4j:slf4j-nop:2.0.6")

    //chat gpt me lo dio nose si esta bien
    implementation ("com.google.code.gson:gson:2.8.8")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(18)
}