package collections

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 4: lazy generation instead of building an eager list up front.
class T4FirstSquareOverTest {
    @Test fun `finds the next square after a small threshold`() {
        assertEquals(16, firstSquareOver(10))
    }

    @Test fun `returns the smallest square when threshold is zero`() {
        assertEquals(1, firstSquareOver(0))
    }

    @Test fun `an exact square as the threshold is excluded`() {
        assertEquals(16, firstSquareOver(9))
    }

    @Test fun `works for a larger threshold`() {
        assertEquals(2601, firstSquareOver(2600))
    }
}
