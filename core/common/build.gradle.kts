plugins {
    alias(libs.plugins.pia.android.library)
}

android {
    namespace = "com.mohaberabi.pastinandroid.core.common"

}

dependencies {

    implementation(libs.data.store)
    implementation(projects.core.commonKotlin)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.firebase.firestore)
}