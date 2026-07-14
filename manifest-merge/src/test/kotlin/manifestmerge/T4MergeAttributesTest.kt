package manifestmerge

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 4: union two attribute maps, app's value wins on shared keys.
class T4MergeAttributesTest {
    @Test fun `disjoint keys are unioned`() {
        val library = mapOf("exported" to "false")
        val app = mapOf("label" to "MyApp")
        assertEquals(mapOf("exported" to "false", "label" to "MyApp"), mergeAttributes(library, app))
    }

    @Test fun `a shared key takes the app's value`() {
        val library = mapOf("exported" to "false", "theme" to "@style/Library")
        val app = mapOf("exported" to "true")
        assertEquals(mapOf("exported" to "true", "theme" to "@style/Library"), mergeAttributes(library, app))
    }

    @Test fun `empty app attributes leaves library attributes unchanged`() {
        val library = mapOf("exported" to "false")
        assertEquals(library, mergeAttributes(library, emptyMap()))
    }
}
