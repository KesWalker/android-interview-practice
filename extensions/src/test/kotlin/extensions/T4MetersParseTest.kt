package extensions

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

// Task 4: extension function on a companion object (factory-call syntax).
class T4MetersParseTest {
    @Test fun `parses valid input`() {
        val result = Meters.parse("5.5m")
        assertEquals(5.5, result?.value)
    }

    @Test fun `returns null for missing suffix`() = assertNull(Meters.parse("5.5"))

    @Test fun `returns null for non numeric value`() = assertNull(Meters.parse("abcm"))
}
