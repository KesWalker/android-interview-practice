package semantics

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 2: the and/or/not combinators finders are assembled from.
class T2MatcherCombinatorsTest {
    private val enabledNode = SemanticsNode(text = "Save", enabled = true)
    private val disabledNode = SemanticsNode(text = "Save", enabled = false)
    private val isSave: Matcher = hasText("Save")
    private val isEnabled: Matcher = { it.enabled }

    @Test fun `and requires both matchers to accept the node`() {
        val matcher = and(isSave, isEnabled)

        assertTrue(matcher(enabledNode))
        assertFalse(matcher(disabledNode))
        assertFalse(matcher(SemanticsNode(text = "Cancel", enabled = true)))
    }

    @Test fun `or accepts the node when either matcher does`() {
        val matcher = or(hasText("Save"), hasText("Cancel"))

        assertTrue(matcher(SemanticsNode(text = "Save")))
        assertTrue(matcher(SemanticsNode(text = "Cancel")))
        assertFalse(matcher(SemanticsNode(text = "Delete")))
    }

    @Test fun `not inverts the matcher's result`() {
        val matcher = not(hasText("Save"))

        assertFalse(matcher(enabledNode))
        assertTrue(matcher(SemanticsNode(text = "Cancel")))
    }
}
