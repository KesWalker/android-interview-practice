package prefstore

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

// Task 2: type-safe keys, reading at the wrong type must fail loudly.
class T2GetTypedTest {
    private val count = intKey("count")

    @Test fun `returns null when the key is missing`() {
        assertNull(Preferences(emptyMap()).getTyped(count))
    }

    @Test fun `returns the value when it matches the key's type`() {
        val prefs = Preferences(mapOf("count" to 5))
        assertEquals(5, prefs.getTyped(count))
    }

    @Test fun `throws when the stored value doesn't match the key's type`() {
        val prefs = Preferences(mapOf("count" to 5))
        val wrongKey = stringKey("count")
        assertThrows(WrongPreferenceTypeException::class.java) {
            prefs.getTyped(wrongKey)
        }
    }
}
