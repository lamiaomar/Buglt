plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
//    id("com.android.library")
//    `kotlin-dsl`
}

android {
    namespace = "com.example.buglt"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.buglt"
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
}

dependencies {
    var lifecycle_version = "2.6.2"
    var arch_version = "2.2.0"

    implementation(project(":repo:kotlin"))

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Room components
    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")

    // KotlinX Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2")

    // DataStore Preferences
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Coil
    implementation("io.coil-kt:coil-compose:2.5.0")


    // Swipe to Refresh - Accompanist
    implementation("com.google.accompanist:accompanist-swiperefresh:0.21.2-beta")


    // System UI Controller - Accompanist
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.21.2-beta")


    // Palette API
    implementation("androidx.palette:palette-ktx:1.0.0")

    // Testing
    androidTestImplementation("androidx.test:runner:1.4.0")
    androidTestImplementation("androidx.test:rules:1.4.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.0.5")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.0.5")

    testImplementation("junit:junit:4.13.2")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.5.31")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.1")

    // Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.50")
    annotationProcessor("com.google.dagger:hilt-android-compiler:2.43.2")
    annotationProcessor("androidx.hilt:hilt-compiler:1.1.0")
    annotationProcessor("androidx.hilt:hilt-navigation-compose:1.1.0")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    // ViewModel utilities for Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    // Lifecycles only (without ViewModel or LiveData)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")
    // Lifecycle utilities for Compose
    implementation("androidx.lifecycle:lifecycle-runtime-compose:$lifecycle_version")

    // Saved state module for ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycle_version")

    // Annotation processor
    annotationProcessor("androidx.lifecycle:lifecycle-compiler:$lifecycle_version")
    // alternately - if using Java8, use the following instead of lifecycle-compiler
    implementation("androidx.lifecycle:lifecycle-common-java8:$lifecycle_version")

    // optional - helpers for implementing LifecycleOwner in a Service
    implementation("androidx.lifecycle:lifecycle-service:$lifecycle_version")

    // optional - ProcessLifecycleOwner provides a lifecycle for the whole application process
    implementation("androidx.lifecycle:lifecycle-process:$lifecycle_version")

    // optional - ReactiveStreams support for LiveData
    implementation("androidx.lifecycle:lifecycle-reactivestreams-ktx:$lifecycle_version")

    // optional - Test helpers for LiveData
    testImplementation("androidx.arch.core:core-testing:$arch_version")

    // optional - Test helpers for Lifecycle runtime
    testImplementation("androidx.lifecycle:lifecycle-runtime-testing:$lifecycle_version")

    // Horizontal Pager and Indicators - Accompanist
    implementation("com.google.accompanist:accompanist-pager:0.21.2-beta")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.21.2-beta")

    // TODO Handle all the gradle config like below
    implementation(Lifecycle.runtime)
    implementation(Lifecycle.java8)
    annotationProcessor(Lifecycle.compiler)
    implementation(Lifecycle.viewmodel)
    implementation(Lifecycle.livedata)
    implementation(Lifecycle.process)
    implementation(Lifecycle.service)
    implementation(Navigation.fragment)
    implementation(Navigation.ui)
    implementation(Navigation.runtime)


    // Compose dependencies
    implementation("androidx.navigation:navigation-compose:2.7.6")
    implementation("androidx.compose.material:material-icons-extended:1.5.4")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    implementation("androidx.core:core-splashscreen:1.0.1")

}

object Version {
    const val lifecycle = "2.6.0-alpha01"
    const val navigation = "2.5.3"
}

object Lifecycle {
    const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycle}"
    const val java8 = "androidx.lifecycle:lifecycle-common-java8:${Version.lifecycle}"
    const val compiler = "androidx.lifecycle:lifecycle-compiler:${Version.lifecycle}"
    const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycle}"
    const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Version.lifecycle}"
    const val process = "androidx.lifecycle:lifecycle-process:${Version.lifecycle}"
    const val service = "androidx.lifecycle:lifecycle-service:${Version.lifecycle}"
}

object Navigation {
    const val safeArgsPlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:${Version.navigation}"
    const val fragment = "androidx.navigation:navigation-fragment-ktx:${Version.navigation}"
    const val ui = "androidx.navigation:navigation-ui-ktx:${Version.navigation}"
    const val runtime = "androidx.navigation:navigation-runtime-ktx:${Version.navigation}"
}