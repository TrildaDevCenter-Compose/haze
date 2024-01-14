// Copyright 2024, Christopher Banes and the Haze project contributors
// SPDX-License-Identifier: Apache-2.0


import com.android.build.api.dsl.ManagedVirtualDevice

plugins {
  id("dev.chrisbanes.android.test")
  id("dev.chrisbanes.kotlin.android")
  id("androidx.baselineprofile")
}

android {
  namespace = "dev.chrisbanes.haze.baselineprofile.jetpackcompose"

  defaultConfig {
    minSdk = 28
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  targetProjectPath = ":sample:android-jetpack"
  experimentalProperties["android.experimental.self-instrumenting"] = true

  testOptions.managedDevices.devices {
    create<ManagedVirtualDevice>("pixel5Api34") {
      device = "Pixel 5"
      apiLevel = 34
      systemImageSource = "aosp"
    }

    create<ManagedVirtualDevice>("pixel5Api30") {
      device = "Pixel 5"
      apiLevel = 30
      systemImageSource = "aosp"
    }
  }
}

baselineProfile {
  managedDevices += "pixel5Api34"
  managedDevices += "pixel5Api30"
  useConnectedDevices = false
}

dependencies {
  implementation(libs.androidx.benchmark.macro)
  implementation(libs.androidx.test.ext.junit)
  implementation(libs.androidx.test.uiautomator)
}
