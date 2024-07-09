pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "PastInAndroid"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":core:data")
include(":core:domain")
include(":core:database")
include(":core:presenentation:designsystem")
include(":foryou:presentation")
include(":search:presentation")
include(":saved:presentation")
include(":interests:presentation")
include(":core:model")
include(":core:common")
include(":core:common_kotlin")
include(":core:presenentation:ui")
include(":settings")
