plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.kapt) // ✅ 정확한 alias
    alias(libs.plugins.dagger.hilt.android)
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin") // ✅ 이거 꼭 있어야 함
}

android {
    namespace = "com.kimthreemun.indieconcertapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.kimthreemun.indieconcertapp"
        minSdk = 26
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
        compose = true
    }
}

dependencies {
    // AndroidX & Jetpack Compose
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // Navigation (명시된 버전 유지)
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")

    // UI & Animation
    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation("com.github.kizitonwose:CalendarView:1.0.4")
    implementation("com.tbuonomo:dotsindicator:4.3")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.material:material:1.11.0")
    implementation("com.google.android.flexbox:flexbox:3.0.0")


    implementation("com.naver.maps:map-sdk:3.21.0")
    // DI & 이미지 로딩
    implementation(libs.glide)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

// ✅ kapt 블록은 반드시 여기서!
kapt {
    correctErrorTypes = true
}
