package doubles

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class ValidateSignupEmailTest {

    @Test
    fun `a valid email is returned unchanged`() {
        assertEquals("ada@example.com", validateSignupEmail("ada@example.com"))
    }

    @Test
    fun `a blank email throws`() {
        assertThrows(IllegalArgumentException::class.java) {
            validateSignupEmail("")
        }
    }

    @Test
    fun `an email missing the at sign throws`() {
        assertThrows(IllegalArgumentException::class.java) {
            validateSignupEmail("adaexample.com")
        }
    }
}
