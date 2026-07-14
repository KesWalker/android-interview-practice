package variants

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 4: resolve merged files for a variant, missing layers are skipped.
class T4MergedSourceSetFilesTest {
    @Test fun `full stack, variant-specific layer wins over everything`() {
        val sourceSets = mapOf(
            "main" to mapOf("AndroidManifest.xml" to "main-manifest", "strings.xml" to "main-strings"),
            "debug" to mapOf("strings.xml" to "debug-strings"),
            "free" to mapOf("config.xml" to "free-config"),
            "freeDebug" to mapOf("AndroidManifest.xml" to "freeDebug-manifest")
        )
        val result = mergedSourceSetFiles(sourceSets, listOf("free"), "debug")
        assertEquals(
            mapOf(
                "AndroidManifest.xml" to "freeDebug-manifest",
                "strings.xml" to "debug-strings",
                "config.xml" to "free-config"
            ),
            result
        )
    }

    @Test fun `missing build type and variant layers are skipped without error`() {
        val sourceSets = mapOf(
            "main" to mapOf("A" to "main-A"),
            "arm" to mapOf("A" to "arm-A"),
            "free" to mapOf("A" to "free-A")
        )
        val result = mergedSourceSetFiles(sourceSets, listOf("arm", "free"), "debug")
        assertEquals(mapOf("A" to "free-A"), result)
    }

    @Test fun `later flavor in the list outranks an earlier flavor on a tie`() {
        val sourceSets = mapOf(
            "arm" to mapOf("A" to "arm-A"),
            "free" to mapOf("A" to "free-A")
        )
        val armFirst = mergedSourceSetFiles(sourceSets, listOf("arm", "free"), "release")
        assertEquals("free-A", armFirst["A"])

        val freeFirst = mergedSourceSetFiles(sourceSets, listOf("free", "arm"), "release")
        assertEquals("arm-A", freeFirst["A"])
    }
}
