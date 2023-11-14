plugins {
    id("java")
}

group = "ru.vodolazhsky.otus"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework:spring-context:5.3.20")
    implementation("com.opencsv:opencsv:5.7.1")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testImplementation("org.mockito:mockito-junit-jupiter:5.7.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
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
