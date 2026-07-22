package nothingany

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class T2RequireNotBlankTest {
    @Test fun `returns non-blank string`() = assertEquals("hello", requireNotBlank("hello"))
    @Test fun `returns single char`() = assertEquals("x", requireNotBlank("x"))

    @Test fun `throws for null`() {
        assertThrows<IllegalArgumentException> { requireNotBlank(null) }
    }

    @Test fun `throws for blank`() {
        assertThrows<IllegalArgumentException> { requireNotBlank("   ") }
    }

    @Test fun `throws for empty`() {
        assertThrows<IllegalArgumentException> { requireNotBlank("") }
    }
}
