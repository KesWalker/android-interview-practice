package manifestmerge

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 6: merge a full library manifest into a full app manifest.
class T6MergeManifestsTest {
    @Test fun `a library-only node passes through unchanged`() {
        val library = listOf(
            ManifestNode("permission:INTERNET", mapOf("protectionLevel" to "normal"), ManifestSource.LIBRARY)
        )
        assertEquals(library, mergeManifests(library, emptyList()))
    }

    @Test fun `an app-only node is appended after library nodes`() {
        val library = listOf(ManifestNode("permission:INTERNET", emptyMap(), ManifestSource.LIBRARY))
        val app = listOf(ManifestNode("activity:.MainActivity", mapOf("exported" to "true"), ManifestSource.APP))
        assertEquals(library + app, mergeManifests(library, app))
    }

    @Test fun `a MERGE override combines attributes for a shared key`() {
        val library = listOf(
            ManifestNode(
                "activity:.MainActivity",
                mapOf("exported" to "false", "theme" to "@style/Library"),
                ManifestSource.LIBRARY
            )
        )
        val app = listOf(
            ManifestNode(
                "activity:.MainActivity",
                mapOf("exported" to "true"),
                ManifestSource.APP,
                NodeMarker.MERGE
            )
        )
        val result = mergeManifests(library, app)
        assertEquals(
            listOf(
                ManifestNode(
                    "activity:.MainActivity",
                    mapOf("exported" to "true", "theme" to "@style/Library"),
                    ManifestSource.APP,
                    NodeMarker.MERGE
                )
            ),
            result
        )
    }

    @Test fun `a REMOVE override drops the node from the merged manifest`() {
        val library = listOf(ManifestNode("permission:READ_CONTACTS", emptyMap(), ManifestSource.LIBRARY))
        val app = listOf(ManifestNode("permission:READ_CONTACTS", emptyMap(), ManifestSource.APP, NodeMarker.REMOVE))
        assertEquals(emptyList<ManifestNode>(), mergeManifests(library, app))
    }
}
