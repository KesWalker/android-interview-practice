package validation

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 1: bind untrusted values instead of splicing them into SQL text.
class T1BuildQueryTest {
    @Test fun `builds a query with a placeholder and a bound arg`() {
        val query = buildQuery("users", "email", "a@example.com")
        assertEquals("SELECT * FROM users WHERE email = ?", query.sql)
        assertEquals(listOf("a@example.com"), query.args)
    }

    @Test fun `an injection attempt in value never changes the sql text`() {
        val query = buildQuery("users", "email", "a' OR '1'='1")
        assertEquals("SELECT * FROM users WHERE email = ?", query.sql)
        assertEquals(listOf("a' OR '1'='1"), query.args)
    }
}
