plugins {
    // Versions declared once here; each topic module applies the plugins it
    // needs without repeating the version.
    kotlin("jvm") version "2.0.21" apply false
    kotlin("android") version "2.0.21" apply false
    kotlin("plugin.compose") version "2.0.21" apply false
    id("com.android.library") version "8.7.3" apply false
    id("io.github.takahirom.roborazzi") version "1.32.2" apply false
}
