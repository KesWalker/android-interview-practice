package backstack

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 3: pop back to a named destination, inclusive or not.
class T3PopUpToTest {
    private val stack = listOf(NavEntry("home"), NavEntry("list"), NavEntry("details"), NavEntry("edit"))

    @Test fun `non-inclusive pop leaves the target on top`() {
        assertEquals(
            listOf(NavEntry("home"), NavEntry("list")),
            popUpTo(stack, "list", inclusive = false)
        )
    }

    @Test fun `inclusive pop also removes the target`() {
        assertEquals(
            listOf(NavEntry("home")),
            popUpTo(stack, "list", inclusive = true)
        )
    }

    @Test fun `uses the occurrence nearest the top when route repeats`() {
        val repeated = listOf(
            NavEntry("home"), NavEntry("details"), NavEntry("list"), NavEntry("details"), NavEntry("edit")
        )
        assertEquals(
            listOf(NavEntry("home"), NavEntry("details"), NavEntry("list"), NavEntry("details")),
            popUpTo(repeated, "details", inclusive = false)
        )
    }

    @Test fun `route not present leaves the stack unchanged`() {
        assertEquals(stack, popUpTo(stack, "missing", inclusive = false))
    }
}
