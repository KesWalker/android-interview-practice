package nullsafety

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

// Task 7: requireNotNull for assert-and-unwrap with a custom message.
class ValidatedConfigTest {
    @Test fun `returns a non-null config unchanged`() = assertEquals("prod.json", validatedConfig("prod.json"))

    @Test fun `throws with the expected message when null`() {
        val exception = assertThrows(IllegalArgumentException::class.java) { validatedConfig(null) }
        assertEquals("config is required", exception.message)
    }
}
