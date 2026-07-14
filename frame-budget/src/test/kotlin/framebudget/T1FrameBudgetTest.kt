package framebudget

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 1: frame budget is just 1000ms divided by the refresh rate.
class T1FrameBudgetTest {
    @Test fun `60Hz gives a 16point67ms budget`() {
        assertEquals(16.666666666666668, frameBudgetMs(60), 0.0001)
    }

    @Test fun `120Hz gives an 8point33ms budget`() {
        assertEquals(8.333333333333334, frameBudgetMs(120), 0.0001)
    }

    @Test fun `90Hz gives an 11point11ms budget`() {
        assertEquals(11.11111111111111, frameBudgetMs(90), 0.0001)
    }
}
