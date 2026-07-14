package startup

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 5: a shared dependency is initialized exactly once.
class T5InitializeAllTest {
    @Test fun `a diamond-shaped dependency is only initialized once`() {
        val initializers = listOf(
            Initializer("d", listOf("b", "c")),
            Initializer("b", listOf("a")),
            Initializer("c", listOf("a")),
            Initializer("a")
        )

        assertEquals(
            mapOf("a" to 1, "b" to 1, "c" to 1, "d" to 1),
            initializeAll(initializers)
        )
    }

    @Test fun `independent initializers each run once`() {
        val initializers = listOf(Initializer("x"), Initializer("y"))

        assertEquals(mapOf("x" to 1, "y" to 1), initializeAll(initializers))
    }
}
