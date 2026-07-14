package backstack

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 5: a nested graph's shared state survives while any of its entries is on the stack.
class T5GraphScopedStateTest {
    @Test fun `keeps a graph's shared state while another of its entries remains`() {
        val state = NavGraphState(
            stack = listOf(
                NavEntry("home"),
                NavEntry("login", graphId = "auth"),
                NavEntry("otp", graphId = "auth")
            ),
            graphSharedState = mapOf("auth" to mapOf("phone" to "555-1234"))
        )
        val result = popBackStackClearingGraphState(state)
        assertEquals(listOf(NavEntry("home"), NavEntry("login", graphId = "auth")), result.stack)
        assertEquals(mapOf("auth" to mapOf("phone" to "555-1234")), result.graphSharedState)
    }

    @Test fun `clears a graph's shared state once its last entry leaves the stack`() {
        val state = NavGraphState(
            stack = listOf(NavEntry("home"), NavEntry("login", graphId = "auth")),
            graphSharedState = mapOf("auth" to mapOf("phone" to "555-1234"))
        )
        val result = popBackStackClearingGraphState(state)
        assertEquals(listOf(NavEntry("home")), result.stack)
        assertEquals(emptyMap<String, Map<String, Any?>>(), result.graphSharedState)
    }

    @Test fun `popping an entry with no graphId leaves other graphs' state untouched`() {
        val state = NavGraphState(
            stack = listOf(NavEntry("home", graphId = "main"), NavEntry("about")),
            graphSharedState = mapOf("main" to mapOf("scrollY" to 40))
        )
        val result = popBackStackClearingGraphState(state)
        assertEquals(listOf(NavEntry("home", graphId = "main")), result.stack)
        assertEquals(mapOf("main" to mapOf("scrollY" to 40)), result.graphSharedState)
    }
}
