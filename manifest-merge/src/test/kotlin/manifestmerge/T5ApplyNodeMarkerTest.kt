package manifestmerge

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

// Task 5: resolve a same-keyed library/app pair using the app's marker.
class T5ApplyNodeMarkerTest {
    private val libraryNode = ManifestNode(
        key = "activity:.MainActivity",
        attributes = mapOf("exported" to "false", "theme" to "@style/Library"),
        source = ManifestSource.LIBRARY
    )

    @Test fun `MERGE combines attributes, app's values win, source becomes APP`() {
        val appNode = ManifestNode(
            key = "activity:.MainActivity",
            attributes = mapOf("exported" to "true"),
            source = ManifestSource.APP,
            marker = NodeMarker.MERGE
        )
        val result = applyNodeMarker(libraryNode, appNode)
        assertEquals(
            ManifestNode(
                key = "activity:.MainActivity",
                attributes = mapOf("exported" to "true", "theme" to "@style/Library"),
                source = ManifestSource.APP,
                marker = NodeMarker.MERGE
            ),
            result
        )
    }

    @Test fun `REPLACE keeps only the app's own attributes`() {
        val appNode = ManifestNode(
            key = "activity:.MainActivity",
            attributes = mapOf("exported" to "true"),
            source = ManifestSource.APP,
            marker = NodeMarker.REPLACE
        )
        val result = applyNodeMarker(libraryNode, appNode)
        assertEquals(
            ManifestNode(
                key = "activity:.MainActivity",
                attributes = mapOf("exported" to "true"),
                source = ManifestSource.APP,
                marker = NodeMarker.REPLACE
            ),
            result
        )
    }

    @Test fun `REMOVE drops the node entirely`() {
        val appNode = ManifestNode(
            key = "activity:.MainActivity",
            attributes = emptyMap(),
            source = ManifestSource.APP,
            marker = NodeMarker.REMOVE
        )
        assertNull(applyNodeMarker(libraryNode, appNode))
    }
}
