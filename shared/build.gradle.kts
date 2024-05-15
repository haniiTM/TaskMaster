plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    id("app.cash.sqldelight")
    kotlin("plugin.serialization")
}

val ktor = "2.2.3"
val sqlDelight = "2.0.0-alpha05"
val koin = "3.2.0"
val multiplatformSettings = "0.8.1"
val napier = "2.4.0"
val kotlinxCoroutines = "1.6.0"
val kotlinxSerialization = "1.2.2"
val coroutines = "1.7.0"
val serialization = "1.4.1"

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "15.6"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
    }
    
    sourceSets {
        commonMain.dependencies {
            // Ktor
            implementation( "io.ktor:ktor-client-core:${ktor}")
            implementation("io.ktor:ktor-serialization-kotlinx-json:${ktor}")
            implementation("io.ktor:ktor-client-content-negotiation:${ktor}")
            implementation("io.ktor:ktor-client-logging:${ktor}")
            implementation("io.ktor:ktor-client-serialization:${ktor}")


            implementation( "org.jetbrains.kotlinx:kotlinx-coroutines-core:${coroutines}")
            implementation( "org.jetbrains.kotlinx:kotlinx-serialization-json:${serialization}")

            // SqlDelight
            implementation("app.cash.sqldelight:coroutines-extensions:${sqlDelight}")
            implementation("app.cash.sqldelight:runtime:${sqlDelight}")
            implementation("app.cash.sqldelight:primitive-adapters:${sqlDelight}")

            // Koin
            implementation( "io.insert-koin:koin-core:${koin}")
            implementation("io.insert-koin:koin-test:${koin}")

            // Russhwolf
            implementation( "com.russhwolf:multiplatform-settings-no-arg:${multiplatformSettings}")

            //Logging
            implementation("io.github.aakira:napier:${napier}")
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        androidMain.dependencies {
            implementation("io.ktor:ktor-client-android:${ktor}")
            implementation("io.ktor:ktor-client-okhttp:${ktor}")

            implementation("app.cash.sqldelight:android-driver:${sqlDelight}")
        }
    }
}

android {
    namespace = "com.example.taskmaster"
    compileSdk = 34
    defaultConfig {
        minSdk = 30
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("com.example.taskmaster.core.database")
        }
    }
}
