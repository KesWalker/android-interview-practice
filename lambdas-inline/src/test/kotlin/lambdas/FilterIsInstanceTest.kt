package lambdas

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 4: a reified type parameter, only possible on an inline function.
class FilterIsInstanceTest {
    @Test fun `keeps only strings`() = assertEquals(listOf("a", "b"), filterIsInstance2<String>(listOf(1, "a", 2.0, "b")))
    @Test fun `keeps only ints`() = assertEquals(listOf(1, 2), filterIsInstance2<Int>(listOf(1, "a", 2, "b")))
    @Test fun `empty result when no matches`() = assertEquals(emptyList<Double>(), filterIsInstance2<Double>(listOf(1, "a", 2)))
}
