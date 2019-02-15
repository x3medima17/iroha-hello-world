import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.10"
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
    maven ( url =  "https://jitpack.io" )

}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    compile("com.github.hyperledger.iroha-java:client:1.0.0_rc2-SNAPSHOT")

}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}