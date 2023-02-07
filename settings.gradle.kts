rootProject.name = "YAMG"

pluginManagement {
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
