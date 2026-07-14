package manifestmerge

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 7: two equal-priority libraries disagreeing with nothing to arbitrate.
class T7FindMergeConflictsTest {
    @Test fun `two library nodes disagreeing on a shared attribute is a conflict`() {
        val nodes = listOf(
            ManifestNode("meta-data:api_key", mapOf("value" to "libA-key"), ManifestSource.LIBRARY),
            ManifestNode("meta-data:api_key", mapOf("value" to "libB-key"), ManifestSource.LIBRARY)
        )
        assertEquals(listOf("meta-data:api_key"), findMergeConflicts(nodes))
    }

    @Test fun `two library nodes agreeing on every shared attribute is not a conflict`() {
        val nodes = listOf(
            ManifestNode("permission:INTERNET", mapOf("protectionLevel" to "normal"), ManifestSource.LIBRARY),
            ManifestNode("permission:INTERNET", mapOf("protectionLevel" to "normal"), ManifestSource.LIBRARY)
        )
        assertEquals(emptyList<String>(), findMergeConflicts(nodes))
    }

    @Test fun `an app node present for the key arbitrates, so it is not a conflict`() {
        val nodes = listOf(
            ManifestNode("meta-data:api_key", mapOf("value" to "libA-key"), ManifestSource.LIBRARY),
            ManifestNode("meta-data:api_key", mapOf("value" to "libB-key"), ManifestSource.LIBRARY),
            ManifestNode("meta-data:api_key", mapOf("value" to "app-key"), ManifestSource.APP)
        )
        assertEquals(emptyList<String>(), findMergeConflicts(nodes))
    }

    @Test fun `only one library node declaring a key is never a conflict`() {
        val nodes = listOf(
            ManifestNode("meta-data:api_key", mapOf("value" to "libA-key"), ManifestSource.LIBRARY),
            ManifestNode("permission:INTERNET", mapOf("protectionLevel" to "normal"), ManifestSource.LIBRARY)
        )
        assertEquals(emptyList<String>(), findMergeConflicts(nodes))
    }
}
