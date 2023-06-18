import com.demo.buildsrc.dependencies.DependencyGroups
import com.demo.buildsrc.extensions.implementation
import com.demo.buildsrc.dependencies.Dependencies
import com.demo.buildsrc.extensions.kapt

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("com.demo.buildsrc.common.base-android-library")
}

dependencies {
    implementation(Dependencies.splashComponent)
    implementation(DependencyGroups.navigationDependencies)
    implementation(DependencyGroups.androidDependencies)
    implementation(DependencyGroups.junitTestImplementationDependencies)
    implementation(DependencyGroups.junitAndroidTestImplementation)
    implementation(Dependencies.glide)
    kapt(Dependencies.glideCompiler)
    implementation(DependencyGroups.hiltDependencies)
    kapt(DependencyGroups.hiltKaptDependencies)
    implementation(DependencyGroups.sizeDependencies)}