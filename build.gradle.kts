plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.seleniumhq.selenium:selenium-java:4.12.1")
    testImplementation("org.testng:testng:7.8.0")
    implementation("io.github.bonigarcia:webdrivermanager:5.5.3")
    implementation("com.aventstack:extentreports:5.0.9")
}

tasks.test {
    useTestNG()
}