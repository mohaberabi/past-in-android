plugins {
    alias(libs.plugins.pia.android.feature.ui)
    alias(libs.plugins.pia.dagger.hilt.android)
}

android {
    namespace = "com.mohaberabi.pastinandroid.settings"

}

dependencies {

    implementation(libs.coil.compose)
    implementation(libs.timber)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    implementation(libs.androidx.activity.compose)
    implementation(projects.core.domain)
    implementation(projects.core.model)
    implementation(projects.core.commonKotlin)
}