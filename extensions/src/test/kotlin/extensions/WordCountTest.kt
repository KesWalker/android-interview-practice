package extensions

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 2: extension property with an explicit getter (no backing field).
class WordCountTest {
    @Test fun `empty string has no words`() = assertEquals(0, "".wordCount)
    @Test fun `blank string has no words`() = assertEquals(0, "   ".wordCount)
    @Test fun `counts words separated by whitespace`() = assertEquals(3, "kotlin is fun".wordCount)
}
