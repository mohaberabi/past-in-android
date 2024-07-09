@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.pia.android.application.compose)
    alias(libs.plugins.pia.dagger.hilt.android)
    alias(libs.plugins.com.google.gms.google.services)

}

android {
    namespace = "com.mohaberabi.pastinandroid"

}

dependencies {

    // Coil
    implementation(libs.coil.compose)

    // Compose
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.navigation.compose)

    // Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)



    api(libs.core)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    implementation(libs.timber)

    implementation(libs.androidx.core.splashscreen)



    implementation(projects.core.domain)
    implementation(projects.core.data)
    implementation(projects.core.database)

    implementation(projects.core.presenentation.designsystem)

    implementation(projects.foryou.presentation)
    implementation(projects.saved.presentation)
    implementation(projects.interests.presentation)
    implementation(projects.core.model)
    implementation(projects.core.presenentation.ui)

    implementation(projects.core.commonKotlin)
    implementation(projects.core.common)
    implementation(projects.settings)
    implementation(projects.search.presentation)

}