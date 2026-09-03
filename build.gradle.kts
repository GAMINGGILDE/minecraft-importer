plugins {
    java
    id("com.gradleup.shadow") version "9.6.1"
}

group = "de.gilde"
val pluginVersion = providers.gradleProperty("releaseVersion").orElse("1.0.5").get()
version = pluginVersion

repositories {
    mavenCentral()
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:26.2.build.121.+")

    implementation("com.zaxxer:HikariCP:7.1.0")
    implementation("org.mariadb.jdbc:mariadb-java-client:3.5.10")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.22.2")

    testImplementation("org.junit.jupiter:junit-jupiter:6.1.3")
    testImplementation("io.papermc.paper:paper-api:26.2.build.121.+")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(25))
}

tasks.processResources {
    filesMatching(listOf("plugin.yml", "paper-plugin.yml")) {
        expand(
            mapOf(
                "version" to pluginVersion
            )
        )
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    options.release.set(25)
}

tasks.shadowJar {
    archiveClassifier.set("")
}

tasks.build {
    dependsOn(tasks.shadowJar)
}

tasks.test {
    useJUnitPlatform()
}
