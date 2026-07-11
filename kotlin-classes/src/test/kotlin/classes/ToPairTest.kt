package classes

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 5: destructuring a data class via componentN().
class ToPairTest {
    @Test fun `destructures into an (x, y) pair`() {
        assertEquals(1 to 2, toPair(Point(1, 2)))
    }

    @Test fun `preserves order for negative coordinates`() {
        assertEquals(-3 to 5, toPair(Point(-3, 5)))
    }

    @Test fun `works for the origin`() {
        assertEquals(0 to 0, toPair(Point(0, 0)))
    }
}
