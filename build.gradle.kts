plugins {
    id("java")
}

group = "ru.vodolazhsky.otus"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter:2.7.17")
    implementation("com.opencsv:opencsv:5.7.1")

    testImplementation("org.springframework.boot:spring-boot-starter-test:2.7.17")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.register<Copy>("copyToLibs") {
    from(configurations.runtimeClasspath)
    into("$buildDir/libs")
}

tasks.jar {
    dependsOn("copyToLibs")
    manifest {
        attributes["Class-Path"] = configurations.runtimeClasspath.get().joinToString(" ") { it.name }
        attributes["Main-Class"] = "ru.vodolazhsky.otus.Runner"
    }
}
