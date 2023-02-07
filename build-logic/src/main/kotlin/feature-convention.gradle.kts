@file:Suppress("UnstableApiUsage")

import gradle.kotlin.dsl.accessors._e94137ebeba9f7b13362602a5c1e987f.android

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