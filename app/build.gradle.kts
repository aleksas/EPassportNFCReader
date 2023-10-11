plugins {
    id("com.android.application")
}

android {
    namespace = "com.alimert.passportreader"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.alimert.passportreader"
        minSdk = 29
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true

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
    buildToolsVersion = "33.0.2"
}

dependencies {
    implementation("androidx.multidex:multidex:2.0.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("org.jmrtd:jmrtd:0.7.40")
    implementation("net.sf.scuba:scuba-sc-android:0.0.20")
    implementation("com.madgag.spongycastle:prov:1.58.0.0")
    implementation("edu.ucar:jj2000:5.2")
    implementation("com.github.mhshams:jnbis:1.1.0")
    implementation("com.gemalto.jp2:jp2-android:1.0.3")

    implementation("com.google.android.gms:play-services-mlkit-text-recognition:19.0.0")

    implementation("com.google.mlkit:camera:16.0.0-beta3")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    implementation("androidx.appcompat:appcompat:1.6.1")

}