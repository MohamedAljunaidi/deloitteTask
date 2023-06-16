import com.demo.buildsrc.dependencies.DependencyGroups
import com.demo.buildsrc.extensions.implementation
import com.demo.buildsrc.dependencies.Dependencies

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
    implementation("com.intuit.ssp:ssp-android:1.0.6")
    implementation("com.intuit.sdp:sdp-android:1.0.6")
}