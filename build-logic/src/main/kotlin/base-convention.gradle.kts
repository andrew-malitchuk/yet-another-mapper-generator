@file:Suppress("UnstableApiUsage")

import gradle.kotlin.dsl.accessors._e94137ebeba9f7b13362602a5c1e987f.android
import gradle.kotlin.dsl.accessors._e94137ebeba9f7b13362602a5c1e987f.java
import gradle.kotlin.dsl.accessors._e94137ebeba9f7b13362602a5c1e987f.kotlinOptions

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
    kotlin("android")
}

android {
    compileSdk = Configuration.Android.TARGET_SDK
    defaultConfig {
        minSdk = Configuration.Android.MIN_SDK
        targetSdk = Configuration.Android.TARGET_SDK
        versionCode = Configuration.Version.VERSION_CODE
        versionName = Configuration.Version.VERSION_NAME
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = Configuration.Compiler.KOTLIN_JVM_VERSION
    }
}

dependencies {
//    implementation(libs.findBundle("android").get())
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}