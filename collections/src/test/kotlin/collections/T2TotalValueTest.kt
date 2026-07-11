package collections

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 2: read-only List<T> is covariant, so a List<Int> already satisfies List<Number>.
class T2TotalValueTest {
    @Test fun `sums a list of ints`() {
        val ints: List<Int> = listOf(1, 2, 3)
        assertEquals(6.0, totalValue(ints))
    }

    @Test fun `sums a list of doubles`() {
        assertEquals(3.5, totalValue(listOf(1.0, 2.5)))
    }

    @Test fun `sums a mix of numeric types`() {
        val mixed: List<Number> = listOf(1, 2.5f, 3L)
        assertEquals(6.5, totalValue(mixed))
    }

    @Test fun `empty list sums to zero`() {
        assertEquals(0.0, totalValue(emptyList()))
    }
}
