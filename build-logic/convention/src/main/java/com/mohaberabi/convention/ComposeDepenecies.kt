package com.mohaberabi.convention

import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project


fun DependencyHandlerScope.addUiLayerDepencies(project: Project) {
    "implementation"(project(":core:presenentation:designsystem"))
    "implementation"(project(":core:presenentation:ui"))

    "implementation"(project.libs.findBundle("compose").get())
    "debugImplementation"(project.libs.findBundle("compose.debug").get())
    "androidTestImplementation"(project.libs.findLibrary("androidx.compose.ui.test.junit4").get())

}