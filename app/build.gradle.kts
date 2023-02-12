@file:Suppress("UnstableApiUsage")

plugins {
    id("application-convention")
    id("com.google.devtools.ksp")
}

android {
    namespace = "dev.yamg.app"

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    sourceSets {
        getByName("debug") {
            java.srcDir(File("build/generated/ksp/debug/kotlin"))
        }
    }
}

ksp {
    arg("ignoreGenericArgs", "false")
}

dependencies {
    implementation(libs.bundles.android)
    implementation(libs.material)
    implementation(project(":core"))
    ksp(project(":processor"))
}