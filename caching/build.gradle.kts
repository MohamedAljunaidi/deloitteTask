import com.demo.buildsrc.dependencies.Dependencies
import com.demo.buildsrc.dependencies.DependencyGroups
import com.demo.buildsrc.extensions.implementation
import com.demo.buildsrc.common.AppConfig
import com.demo.buildsrc.extensions.kapt

plugins {
    kotlin("kapt")
    id("com.demo.buildsrc.common.base-android-library")
}
dependencies {
    implementation(project(AppConfig.ModulePathsConstant.CORE))

    implementation(DependencyGroups.hiltDependencies)
    kapt(DependencyGroups.hiltKaptDependencies)

    implementation(Dependencies.preferencesDataStore)
    kapt(Dependencies.roomCompiler)
    implementation(DependencyGroups.roomDependencies)
    implementation(Dependencies.gson)

    implementation(DependencyGroups.androidDependencies)
    implementation(DependencyGroups.junitTestImplementationDependencies)
    implementation(DependencyGroups.junitAndroidTestImplementation)
}