plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.receitas"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.receitas"
        minSdk = 24
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
}

dependencies {
    // Bibliotecas AndroidX e UI
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    // Volley e RecyclerView (Kotlin DSL)
    implementation("com.android.volley:volley:1.2.1")
    implementation("androidx.recyclerview:recyclerview:1.3.2")

    // Testes
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
