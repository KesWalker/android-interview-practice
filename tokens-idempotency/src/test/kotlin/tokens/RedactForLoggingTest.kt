package tokens

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 7: redacting the Authorization header before logging.
class RedactForLoggingTest {
    @Test fun `masks the Authorization header regardless of casing`() {
        val headers = mapOf("authorization" to "Bearer secret-token", "Accept" to "application/json")

        val redacted = redactForLogging(headers)

        assertEquals("REDACTED", redacted["authorization"])
        assertEquals("application/json", redacted["Accept"])
    }

    @Test fun `headers with no Authorization key at all pass through unchanged`() {
        val headers = mapOf("Accept" to "application/json", "X-Request-Id" to "abc")

        assertEquals(headers, redactForLogging(headers))
    }
}
