@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.application")
    id("base-convention")
}

android {
    defaultConfig {
        applicationId = Configuration.Android.APPLICATION_ID
    }
    buildFeatures {
        viewBinding = true
    }
}