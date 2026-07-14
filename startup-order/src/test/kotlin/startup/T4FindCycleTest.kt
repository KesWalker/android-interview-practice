package startup

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 4: detect a cycle instead of hanging or crashing.
class T4FindCycleTest {
    @Test fun `acyclic graph returns no cycle`() {
        val initializers = listOf(
            Initializer("app", listOf("network")),
            Initializer("network")
        )

        assertTrue(findCycle(initializers).isEmpty())
    }

    @Test fun `two initializers depending on each other form a cycle`() {
        val initializers = listOf(
            Initializer("network", listOf("logging")),
            Initializer("logging", listOf("network"))
        )

        assertEquals(listOf("logging", "network"), findCycle(initializers))
    }

    @Test fun `a three-way cycle is reported starting at its smallest id`() {
        val initializers = listOf(
            Initializer("bravo", listOf("charlie")),
            Initializer("charlie", listOf("alpha")),
            Initializer("alpha", listOf("bravo"))
        )

        assertEquals(listOf("alpha", "bravo", "charlie"), findCycle(initializers))
    }

    @Test fun `an initializer outside the cycle is not included`() {
        val initializers = listOf(
            Initializer("app", listOf("network")),
            Initializer("network", listOf("cache")),
            Initializer("cache", listOf("network"))
        )

        assertEquals(listOf("cache", "network"), findCycle(initializers))
    }
}
