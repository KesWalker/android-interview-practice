package validation

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 3: an allow-list validator that rejects rather than sanitizes.
class T3ValidateUsernameTest {
    @Test fun `a normal username is accepted`() {
        assertEquals(ValidationResult.ACCEPTED, validateUsername("kes_walker1"))
    }

    @Test fun `a username with disallowed characters is rejected outright`() {
        assertEquals(ValidationResult.REJECTED, validateUsername("bad name!"))
    }

    @Test fun `a username carrying a script tag is rejected, not stripped and re-checked`() {
        assertEquals(ValidationResult.REJECTED, validateUsername("kes<script>"))
    }

    @Test fun `too short or too long usernames are rejected`() {
        assertEquals(ValidationResult.REJECTED, validateUsername("ab"))
        assertEquals(ValidationResult.REJECTED, validateUsername("a".repeat(21)))
    }
}
