package generics

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 4: star projection over a bounded, covariant type parameter.
class SumBoxesTest {
    @Test fun `sums a single box`() =
        assertEquals(3.0, sumBoxes(listOf(NumberBox(3))), 0.0001)

    @Test fun `sums boxes holding different Number subtypes`() =
        assertEquals(6.5, sumBoxes(listOf(NumberBox(1), NumberBox(2.5), NumberBox(3L))), 0.0001)

    @Test fun `empty list sums to zero`() =
        assertEquals(0.0, sumBoxes(emptyList()), 0.0001)
}
