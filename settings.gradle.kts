rootProject.name = "YAMG"

pluginManagement {
    plugins {
        id("com.google.devtools.ksp") version "1.8.10-1.0.9"
        kotlin("jvm")
    }
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

includeBuild("build-logic")

include(":app")
include(":core")
include(":processor")
