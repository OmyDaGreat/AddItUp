@file:OptIn(ExperimentalKotlinGradlePluginApi::class)

import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kobweb.application)
}

group = "xyz.malefic.additup"
version = "1.0.0"

kobweb {
    app {
        index {
            description.set("Powered by Kobweb")
        }
    }
}

kotlin {
    configAsKobwebApplication("additup")

    compilerOptions {
        freeCompilerArgs.add("-Xcontext-parameters")
    }

    sourceSets {
        jsMain.dependencies {
            implementation(libs.arrow.core)
            implementation(libs.bundles.compose)
            implementation(libs.bundles.kobweb)
            implementation(libs.bundles.silk.icons)
            implementation(libs.kutint)
        }
    }
}
