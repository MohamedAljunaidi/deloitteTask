import com.demo.buildsrc.common.AppConfig
import com.demo.buildsrc.dependencies.DependencyGroups
import com.demo.buildsrc.extensions.implementation
import com.demo.buildsrc.extensions.kapt

plugins {
    id("com.android.library")
    id("dagger.hilt.android.plugin")
    id("kotlin-android")
    id("kotlin-kapt")
    id("com.demo.buildsrc.common.base-android-library")
}

dependencies {
    implementation(project(AppConfig.ModulePathsConstant.CORE))

    implementation(DependencyGroups.coroutinesDependencies)
    implementation(DependencyGroups.retrofitDependencies)
    implementation(DependencyGroups.hiltDependencies)
    kapt(DependencyGroups.hiltKaptDependencies)

    implementation(DependencyGroups.androidDependencies)
    implementation(DependencyGroups.junitTestImplementationDependencies)
    implementation(DependencyGroups.junitAndroidTestImplementation)
}