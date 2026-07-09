package nullsafety

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 1: safe call + elvis.
class SafeLengthTest {
    @Test fun `null becomes zero`() = assertEquals(0, safeLength(null))
    @Test fun `counts the characters`() = assertEquals(6, safeLength("kotlin"))
    @Test fun `empty string is zero`() = assertEquals(0, safeLength(""))
}
