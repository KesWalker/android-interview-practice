package doubles

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class T3SpyEmailSenderTest {

    @Test
    fun `records every call it forwards`() {
        val spy = SpyEmailSender(StubEmailSender(result = true))

        spy.send("a@example.com", "Welcome!")
        spy.send("b@example.com", "Reminder")

        assertEquals(
            listOf("a@example.com" to "Welcome!", "b@example.com" to "Reminder"),
            spy.sentMessages
        )
    }

    @Test
    fun `returns whatever the wrapped sender returns`() {
        val succeeding = SpyEmailSender(StubEmailSender(result = true))
        val failing = SpyEmailSender(StubEmailSender(result = false))

        assertTrue(succeeding.send("a@example.com", "Hi"))
        assertFalse(failing.send("a@example.com", "Hi"))
    }
}
