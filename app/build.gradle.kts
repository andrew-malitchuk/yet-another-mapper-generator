@file:Suppress("UnstableApiUsage")

plugins {
    id("application-convention")
    id("com.google.devtools.ksp")
}

android {
    namespace = "dev.yamg.app"

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    sourceSets {
        getByName("debug") {
            java.srcDir(File("build/generated/ksp/debug/kotlin"))
        }
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

ksp {
    arg("mappersPackageName", "io.demo.foobar")
}

dependencies {
    implementation(libs.bundles.android)
    implementation(libs.material)
    implementation(project(":core"))
    ksp(project(":processor"))
    implementation(libs.material)
    implementation(libs.material)
    implementation(libs.material)
    implementation(kotlin("reflect"))

    testImplementation(libs.bundles.kotest)
}