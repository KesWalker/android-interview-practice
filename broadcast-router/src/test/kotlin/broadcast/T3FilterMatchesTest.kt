package broadcast

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 3: intent-filter matching by action, category subset and data scheme.
class T3FilterMatchesTest {
    private val filter = IntentFilterSpec(
        actions = setOf("ACTION_A"),
        categories = setOf("CAT_1", "CAT_2"),
        dataScheme = null
    )

    @Test fun `matches when the action is declared and categories are a subset`() {
        assertTrue(filterMatches(filter, BroadcastIntent("ACTION_A", categories = setOf("CAT_1"))))
    }

    @Test fun `an action not declared by the filter never matches`() {
        assertFalse(filterMatches(filter, BroadcastIntent("ACTION_B")))
    }

    @Test fun `a category the filter never declared fails the match`() {
        assertFalse(filterMatches(filter, BroadcastIntent("ACTION_A", categories = setOf("CAT_3"))))
    }

    @Test fun `data scheme must match exactly, including the no-data case`() {
        val withScheme = filter.copy(dataScheme = "content")
        assertTrue(filterMatches(withScheme, BroadcastIntent("ACTION_A", dataScheme = "content")))
        assertFalse(filterMatches(withScheme, BroadcastIntent("ACTION_A", dataScheme = "file")))
        assertFalse(filterMatches(withScheme, BroadcastIntent("ACTION_A", dataScheme = null)))
        assertFalse(filterMatches(filter, BroadcastIntent("ACTION_A", dataScheme = "content")))
    }
}
