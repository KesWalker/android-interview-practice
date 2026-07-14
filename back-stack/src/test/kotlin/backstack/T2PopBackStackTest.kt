package backstack

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 2: pop the top entry, but never below the start destination.
class T2PopBackStackTest {
    @Test fun `removes the top entry`() {
        val stack = listOf(NavEntry("home"), NavEntry("details"))
        assertEquals(listOf(NavEntry("home")), popBackStack(stack))
    }

    @Test fun `is a no-op with only one entry`() {
        val stack = listOf(NavEntry("home"))
        assertEquals(listOf(NavEntry("home")), popBackStack(stack))
    }

    @Test fun `only removes one entry at a time`() {
        val stack = listOf(NavEntry("home"), NavEntry("list"), NavEntry("details"))
        assertEquals(listOf(NavEntry("home"), NavEntry("list")), popBackStack(stack))
    }
}
