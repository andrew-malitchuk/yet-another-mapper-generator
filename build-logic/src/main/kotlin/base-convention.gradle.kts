@file:Suppress("UnstableApiUsage")

import gradle.kotlin.dsl.accessors._6d51757f9d240b053e9d04252edc91dd.android
import gradle.kotlin.dsl.accessors._6d51757f9d240b053e9d04252edc91dd.java
import gradle.kotlin.dsl.accessors._6d51757f9d240b053e9d04252edc91dd.kotlinOptions

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

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}