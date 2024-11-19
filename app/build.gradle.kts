plugins {
    id("com.android.application") version "8.5.2"  // Versão explicita do plugin do Android
    id("org.jetbrains.kotlin.android") version "1.9.10" // Versão do plugin Kotlin
}

android {
    namespace = "br.com.fiap.energyx"
    compileSdk = 35

    defaultConfig {
        applicationId = "br.com.fiap.energyx"
        minSdk = 33
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
    implementation(libs.kotlinx.coroutines.android)
    implementation (libs.androidx.appcompat.v160)
    implementation (libs.logging.interceptor.v491)
    implementation (libs.gson)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.retrofit2.retrofit)
    implementation(libs.retrofit2.converter.gson)
}
