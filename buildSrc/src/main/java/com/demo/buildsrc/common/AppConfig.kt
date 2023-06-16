package com.demo.buildsrc.common

/**
 *  The `AppConfig` object held all of the constants that we utilized in our Gradle project.
 */

object AppConfig {

    object AppConfigConstant {
        const val compileSdk = 33
        const val minSdk = 27
        const val targetSdk = 33
        const val versionName = "1"
        const val buildToolsVersion = "31.0.0"
        const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        const val JVM_TARGET = "11"
    }

    object BuildTypeConstant {
        const val RELEASE = "release"
    }


    object ModulePathsConstant {
        const val APP = ":app"
        const val CORE = ":core"
    }

}