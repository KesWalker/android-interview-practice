package startup

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 3: dependency-first order, a diamond dependency appears exactly once.
class T3TopologicalOrderTest {
    @Test fun `a diamond dependency is placed before both its dependents and not duplicated`() {
        val initializers = listOf(
            Initializer("d", listOf("b", "c")),
            Initializer("b", listOf("a")),
            Initializer("c", listOf("a")),
            Initializer("a")
        )

        assertEquals(listOf("a", "b", "c", "d"), topologicalOrder(initializers))
    }

    @Test fun `ties are broken by an initializer's own dependency-list order`() {
        val initializers = listOf(
            Initializer("m", listOf("p", "q")),
            Initializer("q"),
            Initializer("p")
        )

        assertEquals(listOf("p", "q", "m"), topologicalOrder(initializers))
    }

    @Test fun `initializers with no dependencies keep their input order`() {
        val initializers = listOf(
            Initializer("x"),
            Initializer("y"),
            Initializer("z", listOf("x", "y"))
        )

        assertEquals(listOf("x", "y", "z"), topologicalOrder(initializers))
    }
}
