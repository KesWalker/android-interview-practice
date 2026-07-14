package backstack

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 4: launchSingleTop avoids stacking a duplicate of the current top.
class T4NavigateSingleTopTest {
    @Test fun `does not duplicate the current top`() {
        val stack = listOf(NavEntry("home"), NavEntry("details"))
        assertEquals(stack, navigateSingleTop(stack, "details"))
    }

    @Test fun `pushes normally when the top route differs`() {
        val stack = listOf(NavEntry("home"))
        assertEquals(
            listOf(NavEntry("home"), NavEntry("details")),
            navigateSingleTop(stack, "details")
        )
    }

    @Test fun `an earlier occurrence further down is not deduplicated`() {
        val stack = listOf(NavEntry("details"), NavEntry("home"))
        assertEquals(
            listOf(NavEntry("details"), NavEntry("home"), NavEntry("details")),
            navigateSingleTop(stack, "details")
        )
    }
}
