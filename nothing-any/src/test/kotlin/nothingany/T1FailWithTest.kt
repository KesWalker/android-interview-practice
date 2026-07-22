package nothingany

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class T1FailWithTest {
    @Test fun `throws IllegalStateException with message`() {
        val ex = assertThrows<IllegalStateException> { failWith("boom") }
        assertEquals("boom", ex.message)
    }

    @Test fun `throws with empty message`() {
        val ex = assertThrows<IllegalStateException> { failWith("") }
        assertEquals("", ex.message)
    }
}
