package nullsafety

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 3: mapNotNull + toIntOrNull.
class T3ValidAgesTest {
    @Test fun `keeps only the numbers`() =
        assertEquals(listOf(21, 34), validAges(listOf("21", "x", "34", "")))

    @Test fun `all valid stays intact`() =
        assertEquals(listOf(1, 2, 3), validAges(listOf("1", "2", "3")))

    @Test fun `none valid is empty`() =
        assertEquals(emptyList<Int>(), validAges(listOf("a", "b")))
}
