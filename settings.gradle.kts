rootProject.name = "android-interview-practice"

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

// One module per topic. Each is self-contained: open the root once in Android
// Studio (or IntelliJ) and run any topic on its own.
include(":null-safety")
