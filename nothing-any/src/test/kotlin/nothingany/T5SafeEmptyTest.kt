package nothingany

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class T5SafeEmptyTest {
    @Test fun `returns empty list of Strings`() {
        val result: List<String> = safeEmpty()
        assertTrue(result.isEmpty())
    }

    @Test fun `returns empty list of Ints`() {
        val result: List<Int> = safeEmpty()
        assertTrue(result.isEmpty())
    }

    @Test fun `two calls return equal lists`() {
        assertEquals(safeEmpty<String>(), safeEmpty<Int>())
    }
}
