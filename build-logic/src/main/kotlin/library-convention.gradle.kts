@file:Suppress("UnstableApiUsage")

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
    id("com.android.library")
    id("base-convention")
}

dependencies {
//    implementation(libs.findBundle("android").get())
}