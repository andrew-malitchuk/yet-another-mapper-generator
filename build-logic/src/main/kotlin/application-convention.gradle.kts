@file:Suppress("UnstableApiUsage")

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

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

dependencies {
//    implementation(libs.findBundle("android").get())
}