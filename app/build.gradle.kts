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
    testImplementation("io.kotest:kotest-runner-junit5:5.5.5")
    testImplementation("io.kotest:kotest-assertions-core:5.5.5")
    testImplementation("io.kotest:kotest-property:5.5.5")
    implementation(kotlin("reflect"))
}