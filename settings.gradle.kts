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
include(":coroutines-basics")
include(":extensions")
include(":generics-variance")
include(":kotlin-classes")
include(":lambdas-inline")
include(":scope-functions")
include(":collections")
include(":concurrent-collections")
include(":concurrenthashmap")
include(":coroutines-structured")
include(":delegation")
include(":mvi")
include(":properties")
include(":stateflow-sharedflow")
include(":tokens-idempotency")
