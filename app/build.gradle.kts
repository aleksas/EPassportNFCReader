plugins {
    id("com.android.application")
//    id("com.google.gms.google-services")
}

android {
    namespace = "com.alimert.passportreader"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.alimert.passportreader"
        minSdk = 29
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
//    dexOptions {
//        javaMaxHeapSize "4g"
//    }

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
//    implementation(platform("com.google.firebase:firebase-bom:32.3.1"))
//    implementation(platform("com.google.firebase:firebase-bom:29.0.0"))

    implementation("androidx.multidex:multidex:2.0.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("org.jmrtd:jmrtd:0.7.18")
    implementation("net.sf.scuba:scuba-sc-android:0.0.20")
    implementation("com.madgag.spongycastle:prov:1.58.0.0")
    implementation("edu.ucar:jj2000:5.2")
    implementation("com.github.mhshams:jnbis:1.1.0")

    implementation("com.google.android.gms:play-services-mlkit-text-recognition:19.0.0")

//    implementation("androidx.camera:camera-core:1.0.0-beta05")
//    implementation("androidx.camera:camera-camera2:1.0.0-beta05")
    implementation("com.google.mlkit:camera:16.0.0-beta3")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    implementation("androidx.appcompat:appcompat:1.6.1")

//    implementation("com.google.firebase:firebase-ml-vision")
//    implementation("com.google.firebase:firebase-ml-vision-image-label-model")
//
//    implementation("com.google.android.gms:play-services-vision:20.1.1")
//    implementation("com.google.android.gms:play-services-vision-common:19.1.1")
//    implementation("com.google.firebase:firebase-ml-vision-image-label-model:20.0.")

}