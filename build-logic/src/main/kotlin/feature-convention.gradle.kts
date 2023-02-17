@file:Suppress("UnstableApiUsage")

import gradle.kotlin.dsl.accessors._6d51757f9d240b053e9d04252edc91dd.android

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
    id("com.android.library")
    id("base-convention")
}

android {
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
//    implementation(libs.findBundle("android").get())
}