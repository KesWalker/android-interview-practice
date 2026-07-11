package lambdas

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 3: a non-local return out of an inlined lambda.
class T3IndexOfFirstNegativeTest {
    @Test fun `finds the first negative index`() = assertEquals(2, indexOfFirstNegative(listOf(4, 8, -1, 9, -2)))
    @Test fun `returns minus one when none negative`() = assertEquals(-1, indexOfFirstNegative(listOf(1, 2, 3)))
    @Test fun `finds negative at the start`() = assertEquals(0, indexOfFirstNegative(listOf(-5, 1, 2)))
}
