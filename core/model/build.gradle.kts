plugins {
    alias(libs.plugins.pia.jvm.library)

}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    api(libs.kotlinx.datetime)

}