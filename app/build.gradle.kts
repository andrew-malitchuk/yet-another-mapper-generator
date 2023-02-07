@file:Suppress("UnstableApiUsage")

plugins {
    id("application-convention")
}

android {
    namespace = "dev.yamg.app"

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(libs.bundles.android)
    implementation(libs.material)
}