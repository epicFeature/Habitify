plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.detekt)
}

android {
    namespace = "org.eventhorizon.habitify"
    compileSdk = 36

    defaultConfig {
        applicationId = "org.eventhorizon.habitify"
        minSdk = 26
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

detekt {
    config.setFrom(files("$rootDir/detekt.yml"))
    buildUponDefaultConfig = true // наследуем настройки от дефолтных
}

dependencies {
    // Зависимости от модулей
    implementation(project(":navigation"))
    implementation(project(":feature:home"))
    implementation(project(":feature:newhabit"))
    implementation(project(":feature:habitinfo"))
    implementation(project(":feature:onboarding"))
    implementation(project(":ui"))
    implementation(project(":domain"))
    implementation(project(":core"))
    // проверить корректно ли
    implementation(project(":data"))


    //Default android
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.compose.foundation.layout)

    // Pluto
    debugImplementation(libs.pluto)
    releaseImplementation(libs.pluto.no.op)
    debugImplementation(libs.logger)
    releaseImplementation(libs.logger.no.op)
    debugImplementation (libs.datastore.pref)
    releaseImplementation (libs.datastore.pref.no.op)
    debugImplementation (libs.network)
    releaseImplementation (libs.network.no.op)
    debugImplementation (libs.rooms.db)
    releaseImplementation (libs.rooms.db.no.op)

    //Test
    androidTestImplementation(libs.junit)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
    // Kaspresso
    androidTestImplementation(libs.kaspresso)
    androidTestImplementation(libs.kaspresso.compose)
    // Hilt Testing, чтобы внедрять зависимости в тестах
    androidTestImplementation(libs.hilt.android.testing)
    kspAndroidTest(libs.hilt.compiler)
    //Default debug
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //Hilt
    implementation(libs.google.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    //detekt
    compileOnly(libs.detekt.api)
    detektPlugins(project(":detekt-rules"))
}