package doubles

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ScriptedEmailSenderTest {

    @Test
    fun `sending before stubbing throws`() {
        val sender = ScriptedEmailSender()

        assertThrows(IllegalStateException::class.java) {
            sender.send("a@example.com", "Hi")
        }
    }

    @Test
    fun `stubbed to succeed returns true regardless of to or subject`() {
        val sender = ScriptedEmailSender()
        sender.stub(true)

        assertTrue(sender.send("a@example.com", "Hi"))
        assertTrue(sender.send("b@example.com", "A completely different subject"))
    }

    @Test
    fun `re-stubbing changes the result for subsequent calls`() {
        val sender = ScriptedEmailSender()
        sender.stub(true)
        assertTrue(sender.send("a@example.com", "Hi"))

        sender.stub(false)

        assertFalse(sender.send("a@example.com", "Hi"))
    }
}
