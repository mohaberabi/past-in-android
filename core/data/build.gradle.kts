@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.pia.android.library)
    alias(libs.plugins.pia.dagger.hilt.android)
    alias(libs.plugins.kotlin.serialization)

}

android {
    namespace = "com.mohaberabi.pastinandroid.core.data"


}

dependencies {
    implementation(projects.core.commonKotlin)
    implementation(projects.core.common)
    implementation(projects.core.model)

    implementation(projects.core.database)
    implementation(libs.data.store)
    implementation(libs.firebase.firestore)

    implementation(libs.kotlinx.serialization.json)
    implementation(projects.core.domain)

}