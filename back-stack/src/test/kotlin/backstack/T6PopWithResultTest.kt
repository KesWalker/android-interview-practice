package backstack

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 6: return a result to the previous entry before popping.
class T6PopWithResultTest {
    @Test fun `writes the result into the previous entry before popping`() {
        val stack = listOf(NavEntry("list"), NavEntry("edit"))
        val result = popBackStackWithResult(stack, "selectedId", 42)
        assertEquals(
            listOf(NavEntry("list", savedState = mapOf("selectedId" to 42))),
            result
        )
    }

    @Test fun `adds to the previous entry's existing saved state`() {
        val stack = listOf(
            NavEntry("list", savedState = mapOf("scrollY" to 10)),
            NavEntry("edit")
        )
        val result = popBackStackWithResult(stack, "selectedId", 42)
        assertEquals(
            listOf(NavEntry("list", savedState = mapOf("scrollY" to 10, "selectedId" to 42))),
            result
        )
    }

    @Test fun `no-ops with only one entry, there is no previous entry to write into`() {
        val stack = listOf(NavEntry("list"))
        assertEquals(stack, popBackStackWithResult(stack, "selectedId", 42))
    }
}
