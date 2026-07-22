package nothingany

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class T6AsAnyTest {
    @Test fun `counts strings in mixed list`() {
        assertEquals(2, countStrings(listOf("a", 1, "b", 3.0)))
    }

    @Test fun `returns zero for no strings`() {
        assertEquals(0, countStrings(listOf(1, 2, 3)))
    }

    @Test fun `counts all when all are strings`() {
        assertEquals(3, countStrings(listOf("x", "y", "z")))
    }

    @Test fun `returns zero for empty list`() {
        assertEquals(0, countStrings(emptyList()))
    }
}
