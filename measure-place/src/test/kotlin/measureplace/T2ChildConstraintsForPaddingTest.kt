package measureplace

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 2: constraints propagate down, a padding modifier shrinks them.
class T2ChildConstraintsForPaddingTest {
    @Test fun `shrinks min and max on both axes by the padding`() {
        val incoming = Constraints(minWidth = 0, maxWidth = 100, minHeight = 0, maxHeight = 50)
        val result = childConstraintsForPadding(incoming, horizontal = 20, vertical = 10)
        assertEquals(Constraints(minWidth = 0, maxWidth = 80, minHeight = 0, maxHeight = 40), result)
    }

    @Test fun `a nonzero min shrinks too but never below zero`() {
        val incoming = Constraints(minWidth = 30, maxWidth = 100, minHeight = 5, maxHeight = 50)
        val result = childConstraintsForPadding(incoming, horizontal = 40, vertical = 20)
        assertEquals(Constraints(minWidth = 0, maxWidth = 60, minHeight = 0, maxHeight = 30), result)
    }

    @Test fun `an unbounded max stays unbounded`() {
        val incoming = Constraints(minWidth = 0, maxWidth = Int.MAX_VALUE, minHeight = 0, maxHeight = Int.MAX_VALUE)
        val result = childConstraintsForPadding(incoming, horizontal = 20, vertical = 10)
        assertEquals(Int.MAX_VALUE, result.maxWidth)
        assertEquals(Int.MAX_VALUE, result.maxHeight)
        assertEquals(0, result.minWidth)
        assertEquals(0, result.minHeight)
    }
}
