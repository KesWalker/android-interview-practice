package variants

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

// Task 5: precedence search for a single buildConfigField key.
class T5ResolveBuildConfigFieldTest {
    private val fieldsBySourceSet = mapOf(
        "main" to mapOf("API_URL" to "https://prod", "FEATURE_X" to "false"),
        "debug" to mapOf("API_URL" to "https://debug"),
        "free" to mapOf("FEATURE_X" to "true"),
        "freeDebug" to mapOf<String, String>()
    )

    @Test fun `falls back to build type when flavor and variant do not define the key`() {
        assertEquals(
            "https://debug",
            resolveBuildConfigField("API_URL", fieldsBySourceSet, listOf("free"), "debug")
        )
    }

    @Test fun `flavor value wins over main when nothing more specific defines it`() {
        assertEquals(
            "true",
            resolveBuildConfigField("FEATURE_X", fieldsBySourceSet, listOf("free"), "debug")
        )
    }

    @Test fun `unknown key returns null`() {
        assertNull(resolveBuildConfigField("UNKNOWN_KEY", fieldsBySourceSet, listOf("free"), "debug"))
    }
}
