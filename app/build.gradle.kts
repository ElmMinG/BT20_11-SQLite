plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "vn.iotstar.listviewpractice"
    compileSdk = 34 // Giữ nguyên 34 là ổn định nhất hiện tại cho học tập

    buildFeatures {
        dataBinding = true
    }

    defaultConfig {
        applicationId = "vn.iotstar.listviewpractice"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)

    // --- SỬA LỖI Ở ĐÂY ---
    // Thay thế libs.activity bằng phiên bản cụ thể tương thích với SDK 34
    implementation("androidx.activity:activity:1.9.3")
    // --------------------

    implementation(libs.constraintlayout)
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}