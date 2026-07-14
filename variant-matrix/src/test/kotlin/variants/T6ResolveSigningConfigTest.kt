package variants

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

// Task 6: signing config resolution, and the release-with-nothing failure.
class T6ResolveSigningConfigTest {
    @Test fun `flavor signing config overrides the build type's`() {
        val result = resolveSigningConfig(
            buildType = "release",
            flavors = listOf("free"),
            buildTypeSigningConfigs = mapOf("release" to "releaseConfig"),
            flavorSigningConfigs = mapOf("free" to "freeConfig")
        )
        assertEquals("freeConfig", result)
    }

    @Test fun `falls back to build type config when no flavor has one`() {
        val result = resolveSigningConfig(
            buildType = "release",
            flavors = listOf("free"),
            buildTypeSigningConfigs = mapOf("release" to "releaseConfig"),
            flavorSigningConfigs = emptyMap()
        )
        assertEquals("releaseConfig", result)
    }

    @Test fun `release with nothing configured throws`() {
        assertThrows(MissingSigningConfigException::class.java) {
            resolveSigningConfig(
                buildType = "release",
                flavors = listOf("free"),
                buildTypeSigningConfigs = emptyMap(),
                flavorSigningConfigs = emptyMap()
            )
        }
    }

    @Test fun `debug with nothing configured defaults to debug`() {
        val result = resolveSigningConfig(
            buildType = "debug",
            flavors = emptyList(),
            buildTypeSigningConfigs = emptyMap(),
            flavorSigningConfigs = emptyMap()
        )
        assertEquals("debug", result)
    }
}
