package backstack

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 1: push a new entry onto the top of the stack.
class T1NavigateTest {
    @Test fun `pushes a new entry on top`() {
        val stack = listOf(NavEntry("home"))
        assertEquals(listOf(NavEntry("home"), NavEntry("details")), navigate(stack, "details"))
    }

    @Test fun `records the graphId on the new entry`() {
        val stack = listOf(NavEntry("home"))
        val result = navigate(stack, "login", graphId = "auth")
        assertEquals(NavEntry("login", graphId = "auth"), result.last())
    }

    @Test fun `does not mutate the original stack`() {
        val stack = listOf(NavEntry("home"))
        navigate(stack, "details")
        assertEquals(listOf(NavEntry("home")), stack)
    }
}
