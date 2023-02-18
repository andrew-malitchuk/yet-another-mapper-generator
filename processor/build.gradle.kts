@file:Suppress("UnstableApiUsage")

plugins {
    kotlin("jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    implementation(project(":core"))
    implementation(libs.symbolProcessingApi)
    implementation(libs.symbolProcessing)
    implementation(libs.kotlinStdlib)
    implementation(libs.kotlinpoetKsp)
    implementation(kotlin("stdlib"))
}