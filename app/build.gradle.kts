plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
//    kotlin("kapt")
//    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.caripartner"
    compileSdk = 34


    defaultConfig {
        applicationId = "com.example.caripartner"
        minSdk = 30
        targetSdk = 33
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
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("com.google.firebase:firebase-messaging-ktx:23.3.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    //    Firebase dependencies
    implementation(platform("com.google.firebase:firebase-bom:32.5.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-storage")
    implementation("com.google.firebase:firebase-firestore")
    implementation("com.google.firebase:firebase-database")

    implementation("androidx.navigation:navigation-compose:2.7.5")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")

    implementation("io.coil-kt:coil-compose:1.3.2")
    implementation("androidx.compose.material:material-icons-extended:1.3.1")

    implementation("androidx.datastore:datastore-preferences:1.0.0")
//    retrofit for chat
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

//    Chat Activity
    implementation ("androidx.appcompat:appcompat:1.3.1")
    implementation ("androidx.recyclerview:recyclerview:1.2.1")
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.3.9")

//    layout
    implementation ("de.hdodenhof:circleimageview:3.1.0")

//    user activity firebase
    implementation ("com.google.firebase:firebase-messaging:23.3.1") // Gantilah versi dengan versi terbaru




//    Hilt
//    implementation("com.google.dagger:hilt-android:2.44")
//    kapt("com.google.dagger:hilt-android-compiler:2.44")


    implementation("com.alexstyl.swipeablecard:swipeablecard:0.1.0")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.32.0")
    implementation("io.coil-kt:coil-compose:2.5.0")

}

