@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.pia.android.library)
    alias(libs.plugins.pia.android.room)
    alias(libs.plugins.pia.dagger.hilt.android)
}

android {
    namespace = "com.mohaberabi.pastinandroid.core.database"

}

dependencies {

    implementation(projects.core.domain)
    implementation(projects.core.model)
    implementation(projects.core.common)
    implementation(projects.core.commonKotlin)
    implementation(libs.kotlinx.datetime)

}