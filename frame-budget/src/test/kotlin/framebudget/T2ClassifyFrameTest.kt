package framebudget

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 2: smooth, janky or frozen, from a duration and a budget.
class T2ClassifyFrameTest {
    private val budgetMs = 1000.0 / 60

    @Test fun `well under budget is smooth`() {
        assertEquals(FrameClass.SMOOTH, classifyFrame(10.0, budgetMs))
    }

    @Test fun `exactly at budget is still smooth`() {
        assertEquals(FrameClass.SMOOTH, classifyFrame(budgetMs, budgetMs))
    }

    @Test fun `over budget but well under 700ms is janky`() {
        assertEquals(FrameClass.JANKY, classifyFrame(20.0, budgetMs))
    }

    @Test fun `just under 700ms is still janky, not frozen`() {
        assertEquals(FrameClass.JANKY, classifyFrame(699.99, budgetMs))
    }

    @Test fun `700ms exactly is frozen`() {
        assertEquals(FrameClass.FROZEN, classifyFrame(700.0, budgetMs))
    }

    @Test fun `well past 700ms is frozen`() {
        assertEquals(FrameClass.FROZEN, classifyFrame(1500.0, budgetMs))
    }
}
