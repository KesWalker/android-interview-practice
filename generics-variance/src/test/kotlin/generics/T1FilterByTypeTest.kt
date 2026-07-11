package generics

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 1: reified type parameter on an inline function.
class T1FilterByTypeTest {
    @Test fun `keeps only strings`() =
        assertEquals(listOf("a", "b"), filterByType<String>(listOf(1, "a", 2.0, "b", null)))

    @Test fun `keeps only ints`() =
        assertEquals(listOf(1, 2), filterByType<Int>(listOf(1, "a", 2, "b")))

    @Test fun `empty when nothing matches`() =
        assertEquals(emptyList<String>(), filterByType<String>(listOf(1, 2, 3)))
}
