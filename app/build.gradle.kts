import com.demo.buildsrc.common.BaseGradle
import com.demo.buildsrc.common.AppConfig
import com.demo.buildsrc.dependencies.DependencyGroups
import com.demo.buildsrc.dependencies.Dependencies
import com.demo.buildsrc.extensions.implementation
import com.demo.buildsrc.extensions.kapt

plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    id("kotlin-android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    BaseGradle.appGradle(this, project = project)
    kotlinOptions {
        jvmTarget = AppConfig.AppConfigConstant.JVM_TARGET
    }
}

dependencies {
    implementation(project(AppConfig.ModulePathsConstant.CORE))
    implementation(project(AppConfig.ModulePathsConstant.CACHING))
    implementation(project(AppConfig.ModulePathsConstant.NETWORK))

    implementation(Dependencies.swipeRefreshLayout)
    implementation(DependencyGroups.retrofitDependencies)
    implementation(DependencyGroups.androidDependencies)
    implementation(DependencyGroups.constraintDependencies)
    implementation(DependencyGroups.hiltDependencies)
    implementation(DependencyGroups.sizeDependencies)
    implementation(Dependencies.preference)
    kapt(DependencyGroups.hiltKaptDependencies)
    implementation(DependencyGroups.constraintDependencies)
    implementation(Dependencies.splashComponent)
    implementation(DependencyGroups.navigationDependencies)
    implementation(DependencyGroups.junitTestImplementationDependencies)
    implementation(DependencyGroups.junitAndroidTestImplementation)
    implementation(DependencyGroups.mockitoTestImplementation)
}