package framebudget

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

// Task 5: blame the phase that actually blew the budget.
class T5AttributeJankTest {
    private val budgetMs = 1000.0 / 60

    @Test fun `draw is blamed when it dominates the breakdown`() {
        val frame = Frame(
            durationMs = 25.0,
            phases = linkedMapOf("input" to 2.0, "layout" to 3.0, "draw" to 20.0)
        )

        assertEquals("draw", attributeJank(frame, budgetMs))
    }

    @Test fun `layout is blamed when it dominates instead`() {
        val frame = Frame(
            durationMs = 30.0,
            phases = linkedMapOf("measure" to 4.0, "layout" to 18.0, "draw" to 8.0)
        )

        assertEquals("layout", attributeJank(frame, budgetMs))
    }

    @Test fun `a frame within budget is not attributed to anything`() {
        val frame = Frame(
            durationMs = 10.0,
            phases = linkedMapOf("input" to 2.0, "layout" to 3.0, "draw" to 5.0)
        )

        assertNull(attributeJank(frame, budgetMs))
    }
}
