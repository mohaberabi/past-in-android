@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {

    `kotlin-dsl`

}

group = "com.mohaberabi.pastinandroid.buildlogic"

dependencies {


    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)

}
gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "pia.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "pia.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "pia.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "pia.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
  
        register("androidRoom") {
            id = "pia.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }
        register("jvmLibrary") {
            id = "pia.jvm.library"
            implementationClass = "JvmConventionPlugin"
        }
        register("daggerHiltAndroid") {
            id = "pia.dagger.hilt.android"
            implementationClass = "HiltConventionPlugin"
        }
        register("androidFeatureUi") {
            id = "pia.android.feature.ui"
            implementationClass = "AndroidFeatureUiConventionPlugin"
        }
    }
}