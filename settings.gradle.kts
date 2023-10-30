enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
plugins {
    id("com.highcapable.sweetdependency") version "1.0.2"
    id("com.highcapable.sweetproperty") version "1.0.3"
}
sweetProperty {
    global { sourcesCode { isEnable = false } }
    rootProject { all { isEnable = false } }
}
rootProject.name = "pagecurl-multiplatform"
include(":samples:androidApp", ":samples:desktopApp", ":samples:shared")
include(":pagecurl")