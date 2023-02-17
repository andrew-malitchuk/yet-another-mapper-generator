val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
    kotlin("jvm")
    id("kotlin")
}

dependencies {

    implementation(libs.kotlinReflect)
    implementation(libs.kotlinStdlib)

}