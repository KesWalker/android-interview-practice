package doubles

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class StubEmailSenderTest {

    @Test
    fun `configured to succeed always returns true`() {
        val sender = StubEmailSender(result = true)

        assertTrue(sender.send("a@example.com", "Hi"))
        assertTrue(sender.send("b@example.com", "A totally different subject"))
    }

    @Test
    fun `configured to fail always returns false`() {
        val sender = StubEmailSender(result = false)

        assertFalse(sender.send("a@example.com", "Hi"))
        assertFalse(sender.send("", ""))
    }
}
