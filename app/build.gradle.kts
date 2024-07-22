

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)

    id("kotlin-kapt")
//    id("com.google.dagger.hilt.android")
    id ("dagger.hilt.android.plugin")

//    id("multiplatform")


    id ("kotlin-android")









}

android {
    namespace = "com.example.chatterboticaapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.chatterboticaapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

//// Allow references to generated code
kapt {
    correctErrorTypes = true
}
//
//hilt {
//    enableAggregatingTask = true
//}


dependencies {

    //PDF
    implementation("com.itextpdf:itext7-core:7.2.2")
    //Room
    val roomversion = "2.6.1"

    implementation("androidx.room:room-runtime:$roomversion")
    annotationProcessor("androidx.room:room-compiler:$roomversion")

    // To use Kotlin annotation processing tool (kapt)
    kapt("androidx.room:room-compiler:$roomversion")
    // To use Kotlin Symbol Processing (KSP)
//    ksp("androidx.room:room-compiler:$room_version")

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$roomversion")

    // optional - RxJava2 support for Room
    implementation("androidx.room:room-rxjava2:$roomversion")

    // optional - RxJava3 support for Room
    implementation("androidx.room:room-rxjava3:$roomversion")

    // optional - Guava support for Room, including Optional and ListenableFuture
    implementation("androidx.room:room-guava:$roomversion")

    // optional - Test helpers
    testImplementation("androidx.room:room-testing:$roomversion")

    // optional - Paging 3 Integration
    implementation("androidx.room:room-paging:$roomversion")

    //Media3
//    implementation ("androidx.media3:media3-exoplayer:1.3.0")
//    implementation ("androidx.media3:media3-ui:1.3.0")

    implementation("androidx.media3:media3-exoplayer:1.3.1")
    implementation("androidx.media3:media3-exoplayer-dash:1.3.1")
    implementation("androidx.media3:media3-ui:1.3.1")



    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    implementation("nl.marc-apps:tts:2.5.0")

    // Optional: Extensions for Compose
    implementation("nl.marc-apps:tts-compose:2.5.0")

    implementation("com.google.ai.client.generativeai:generativeai:0.4.0")

    implementation ("com.google.dagger:hilt-android:2.46")
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.runtime.livedata)
    kapt ("com.google.dagger:hilt-android-compiler:2.46")
//    implementation ("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0")
//    kapt ("androidx.hilt:hilt-compiler:1.0.0")
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")

    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.material.icons.extended)

//    retrofit and okhttp
    implementation (libs.retrofit)
    implementation (libs.okhttp)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}