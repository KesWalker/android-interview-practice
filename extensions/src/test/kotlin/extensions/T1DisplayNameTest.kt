package extensions

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 1: extension function on a nullable receiver.
class T1DisplayNameTest {
    @Test fun `null becomes Unknown`() = assertEquals("Unknown", null.displayNameOrUnknown())
    @Test fun `blank becomes Unknown`() = assertEquals("Unknown", "   ".displayNameOrUnknown())
    @Test fun `trims a real name`() = assertEquals("Kes Walker", "  Kes Walker  ".displayNameOrUnknown())
}
