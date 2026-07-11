package nullsafety

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 5: local val copy to work around the mutable-var smart-cast limitation.
class TokenStatusTest {
    @Test fun `describes a set token`() {
        val cache = SessionCache().apply { token = "abc123" }
        assertEquals("Token: abc123 (6 chars)", tokenStatus(cache))
    }

    @Test fun `reports no token set`() {
        val cache = SessionCache()
        assertEquals("No token set", tokenStatus(cache))
    }

    @Test fun `counts characters correctly for a longer token`() {
        val cache = SessionCache().apply { token = "supersecrettoken" }
        assertEquals("Token: supersecrettoken (16 chars)", tokenStatus(cache))
    }
}
