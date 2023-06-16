package com.demo.buildsrc.buildtype

import com.android.build.api.dsl.LibraryExtension
import com.demo.buildsrc.common.AppConfig.BuildTypeConstant
import org.gradle.api.Project

/**
 *
 * The `BuildTypeLibrary` class for (Library plugin).
 * Here, we set up our build types, such as debug, staging, and release,
 * as well as any configurations, such as buildConfigField and manifestPlaceholders.
 */

object BuildTypeLibrary {

    fun initLibraryBuildType(libraryExtension: LibraryExtension, project: Project) {

        libraryExtension.apply {
            buildTypes {
                maybeCreate(BuildTypeConstant.RELEASE)
                getByName(BuildTypeConstant.RELEASE) {
                    isMinifyEnabled = false
                }
            }
        }
    }
}