package com.demo.buildsrc.buildtype

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.demo.buildsrc.common.AppConfig.BuildTypeConstant
import org.gradle.api.Project

/**
 * The `BuildTypeApp` class for (application plugin).
 * Here, we set up our build types, such as debug, staging, and release,
 * as well as any configurations, such as buildConfigField and manifestPlaceholders.
 */

object BuildTypeApp {

    fun initAppBuildType(baseAppModuleExtension: BaseAppModuleExtension, project: Project) {

        baseAppModuleExtension.apply {
            buildTypes {
                maybeCreate(BuildTypeConstant.RELEASE)
                getByName(BuildTypeConstant.RELEASE) {
                    isMinifyEnabled = false
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }
            }
        }
    }
}