package validation

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 2: flag a query string assembled by concatenating untrusted input.
class T2HasInjectionSmellTest {
    @Test fun `a parameterized query has no smell`() {
        assertFalse(hasInjectionSmell("SELECT * FROM users WHERE email = ?"))
    }

    @Test fun `a spliced equality literal is smelly`() {
        assertTrue(hasInjectionSmell("SELECT * FROM users WHERE email = 'a@example.com'"))
    }

    @Test fun `a spliced LIKE literal is smelly`() {
        assertTrue(hasInjectionSmell("SELECT * FROM users WHERE name LIKE 'bob%'"))
    }

    @Test fun `a classic tautology payload spliced into the query is smelly`() {
        assertTrue(hasInjectionSmell("SELECT * FROM users WHERE email = 'x' OR '1'='1'"))
    }
}
