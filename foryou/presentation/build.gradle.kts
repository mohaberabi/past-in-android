@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.pia.android.feature.ui)
    alias(libs.plugins.pia.dagger.hilt.android)

}

android {
    namespace = "com.mohaberabi.pastinandroid.foryou.presentation"

}

dependencies {
    implementation(libs.timber)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    implementation(libs.androidx.activity.compose)
    implementation(projects.core.domain)
    implementation(projects.core.model)
    implementation(projects.core.commonKotlin)
    implementation(libs.coil.compose)

    implementation(projects.core.common)
}