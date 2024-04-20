val koin = "3.2.0"
val napier = "2.4.0"
val navigation = "2.4.0-alpha02"
val accompanist = "0.20.0"

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.example.taskmaster.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.example.taskmaster.android"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(projects.shared)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation("me.saket.swipe:swipe:1.2.0")
    implementation(libs.compose.material3)
    implementation(libs.androidx.activity.compose)
    implementation("io.insert-koin:koin-android:${koin}")
    implementation("io.insert-koin:koin-androidx-compose:${koin}")
    implementation("io.github.aakira:napier:${napier}")
    implementation("androidx.navigation:navigation-compose:${navigation}")
    implementation("com.google.accompanist:accompanist-navigation-animation:${accompanist}")
    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.27.0")


    //implementation(libs.androidx.navigation.runtime.ktx)
    implementation ("com.google.accompanist:accompanist-navigation-animation:0.20.0")
    debugImplementation(libs.compose.ui.tooling)
}