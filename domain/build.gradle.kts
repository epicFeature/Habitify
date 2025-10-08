plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.detekt)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

detekt {
    config.setFrom(files("$rootDir/detekt.yml"))
    buildUponDefaultConfig = true // наследуем настройки от дефолтных
}

dependencies{
    //inject
    implementation(libs.javax.inject)

    //coroutines+flow
    implementation(libs.kotlinx.coroutines.core)

    //Default test
    testImplementation(libs.junit)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.kotlinx.coroutines.test)

    //detekt
    compileOnly(libs.detekt.api)
    detektPlugins(project(":detekt-rules"))
}