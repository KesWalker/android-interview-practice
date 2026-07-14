package securestore

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 6: scrub secrets out of a log line.
class T6RedactTest {
    @Test fun `a secret appearing once is replaced`() {
        assertEquals(
            "Authorization: [REDACTED]",
            redact("Authorization: abc123token", listOf("abc123token"))
        )
    }

    @Test fun `every occurrence of a secret is replaced`() {
        assertEquals(
            "[REDACTED] then [REDACTED] again",
            redact("abc123 then abc123 again", listOf("abc123"))
        )
    }

    @Test fun `multiple different secrets are each replaced`() {
        assertEquals(
            "user=[REDACTED] token=[REDACTED]",
            redact("user=kes token=xyz", listOf("kes", "xyz"))
        )
    }

    @Test fun `a line with no secrets is returned unchanged`() {
        assertEquals("nothing sensitive here", redact("nothing sensitive here", listOf("abc123")))
    }

    @Test fun `blank entries in secrets are ignored`() {
        assertEquals(
            "user=[REDACTED]",
            redact("user=kes", listOf("", "kes"))
        )
    }
}
