package deeplink

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 5: action must be declared by the filter, then every intent category
// must appear in the filter's categories.
class T5MatchesFilterTest {
    @Test fun `action match with every intent category present in a filter with extras is true`() {
        val intent = IntentSpec("android.intent.action.VIEW", setOf("android.intent.category.DEFAULT"))
        val filter = IntentFilterSpec(
            actions = setOf("android.intent.action.VIEW"),
            categories = setOf("android.intent.category.DEFAULT", "android.intent.category.BROWSABLE"),
        )
        assertTrue(matchesFilter(intent, filter))
    }

    @Test fun `mismatched action is false`() {
        val intent = IntentSpec("android.intent.action.SEND", setOf("android.intent.category.DEFAULT"))
        val filter = IntentFilterSpec(
            actions = setOf("android.intent.action.VIEW"),
            categories = setOf("android.intent.category.DEFAULT"),
        )
        assertFalse(matchesFilter(intent, filter))
    }

    @Test fun `intent category missing from the filter is false`() {
        val intent = IntentSpec("android.intent.action.VIEW", setOf("android.intent.category.DEFAULT"))
        val filter = IntentFilterSpec(
            actions = setOf("android.intent.action.VIEW"),
            categories = setOf("android.intent.category.BROWSABLE"),
        )
        assertFalse(matchesFilter(intent, filter))
    }

    @Test fun `category-free intent matches any filter with the right action`() {
        val intent = IntentSpec("android.intent.action.VIEW")
        val filter = IntentFilterSpec(
            actions = setOf("android.intent.action.VIEW"),
            categories = setOf("android.intent.category.DEFAULT", "android.intent.category.BROWSABLE"),
        )
        assertTrue(matchesFilter(intent, filter))
    }
}
