plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "org.eventhorizon.habitify.database"
    compileSdk = 36

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    //basic
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    //hilt
    implementation(libs.google.hilt.android)
    ksp(libs.hilt.compiler)

    //room
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    implementation(libs.converter.gson)
    ksp(libs.androidx.room.compiler) // Для Kotlin
    implementation(libs.androidx.room.ktx) // Kotlin Extensions

    //datastore
    implementation(libs.androidx.datastore.preferences)

    // Pluto
    debugImplementation (libs.pluto)
    releaseImplementation (libs.pluto.no.op)
    debugImplementation (libs.logger)
    releaseImplementation (libs.logger.no.op)
    debugImplementation (libs.rooms.db)
    releaseImplementation (libs.rooms.db.no.op)

    //coroutine todo нужны ли они
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    //test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}