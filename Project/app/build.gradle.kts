plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.example.project"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.project"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    packaging{
        resources.excludes.add("META-INF/NOTICE.md")
        resources.excludes.add("META-INF/LICENSE.md")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.gson)
    implementation(libs.android.mail)
    implementation("com.sun.mail:android-mail:1.6.7")
    implementation("com.sun.mail:android-activation:1.6.7")
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}