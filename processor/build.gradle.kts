@file:Suppress("UnstableApiUsage")

// TODO: clean-up
plugins {
//    id("com.android.library")
//    kotlin("android")
    kotlin("jvm")
}

//android {
//    namespace = "dev.yamg.processor"
//    compileSdk = 33
//    defaultConfig {
//        minSdk = 24
//        targetSdk = 33
//    }
//    buildTypes {
//        getByName("release") {
//            isMinifyEnabled = false
//        }
//        getByName("release") {
//            isMinifyEnabled = false
//        }
//    }
//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_1_8
//        targetCompatibility = JavaVersion.VERSION_1_8
//    }
//    kotlinOptions {
//        jvmTarget = "1.8"
//    }
//}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    implementation(project(":core"))
    implementation("com.google.devtools.ksp:symbol-processing-api:1.8.10-1.0.9")
    implementation("com.google.devtools.ksp:symbol-processing:1.8.10-1.0.9")
    implementation("com.squareup:kotlinpoet-ksp:1.12.0")


}