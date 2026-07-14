package startup

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 6: lazy initializers are skipped at startup and their cost is counted as saved.
class T6PlanStartupTest {
    @Test fun `lazy initializers are deferred and their cost is saved`() {
        val initializers = listOf(
            Initializer("crashReporter", costMs = 40),
            Initializer("network", listOf("crashReporter"), costMs = 60),
            Initializer("imageDecoderPool", costMs = 150, lazy = true),
            Initializer("analytics", costMs = 25, lazy = true)
        )

        val plan = planStartup(initializers)

        assertEquals(listOf("crashReporter", "network"), plan.eager)
        assertEquals(listOf("analytics", "imageDecoderPool"), plan.deferred)
        assertEquals(175L, plan.costSavedMs)
    }

    @Test fun `no lazy initializers means nothing is deferred`() {
        val initializers = listOf(
            Initializer("a", costMs = 10),
            Initializer("b", listOf("a"), costMs = 20)
        )

        val plan = planStartup(initializers)

        assertEquals(listOf("a", "b"), plan.eager)
        assertEquals(emptyList<String>(), plan.deferred)
        assertEquals(0L, plan.costSavedMs)
    }
}
