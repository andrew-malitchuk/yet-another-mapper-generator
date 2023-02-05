rootProject.name = "YAMG"

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    versionCatalogs {
        create("libs") {
            from(files("./libs.versions.toml"))
        }
    }
}

include(":app")
include(":core")
include(":processor")
