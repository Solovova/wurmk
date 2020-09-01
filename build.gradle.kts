import org.gradle.internal.os.OperatingSystem
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.3.72"
    application
    java
    id("com.github.johnrengelman.shadow") version "4.0.4"
}

repositories {
    jcenter()
}

group = "com.solovova.wurmk"
version = "1.0-SNAPSHOT"

val os: OperatingSystem = OperatingSystem.current()
val platform = when { os.isWindows -> "win"; os.isLinux-> "linux"; os.isMacOsX -> "mac"; else -> error("Unknown OS") }

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")

    implementation("org.openjfx:javafx-fxml:14:$platform")
    implementation("org.openjfx:javafx-web:14:$platform")
    implementation("org.openjfx:javafx-media:14:$platform")
    implementation("org.openjfx:javafx-swing:14:$platform")
    implementation("org.openjfx:javafx-base:14:$platform")
    implementation("org.openjfx:javafx-graphics:14:$platform")
    implementation("org.openjfx:javafx-controls:14:$platform")

    implementation("org.ini4j:ini4j:0.5.4")
}

application {
    // Define the main class for the application.
    mainClassName = "com.solovova.wurmk.MainGUI"
}

tasks {
    named<ShadowJar>("shadowJar") {
        archiveBaseName.set("shadow")
        mergeServiceFiles()
        manifest {
            attributes(mapOf("Main-Class" to "com.solovova.wurmk.MainGUI"))
        }
    }
}

tasks {
    build {
        dependsOn(shadowJar)
    }
}
