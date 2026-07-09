package doubles

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class SignupServiceTest {

    @Test
    fun `new email is saved and welcomed`() {
        val store = InMemoryUserStore()
        val spy = SpyEmailSender(StubEmailSender(result = true))
        val service = SignupService(store, spy)

        val result = service.signUp("ada@example.com", "Ada")

        assertEquals(SignupResult.SUCCESS, result)
        assertTrue(store.exists("ada@example.com"))
        assertEquals(listOf("ada@example.com" to WELCOME_SUBJECT), spy.sentMessages)
    }

    @Test
    fun `already registered email is rejected without sending`() {
        val store = InMemoryUserStore()
        store.save(User("ada@example.com", "Ada"))
        val spy = SpyEmailSender(StubEmailSender(result = true))
        val service = SignupService(store, spy)

        val result = service.signUp("ada@example.com", "Ada again")

        assertEquals(SignupResult.ALREADY_REGISTERED, result)
        assertEquals(1, store.all().size)
        assertTrue(spy.sentMessages.isEmpty())
    }

    @Test
    fun `user is still saved when the welcome email fails to send`() {
        val store = InMemoryUserStore()
        val spy = SpyEmailSender(StubEmailSender(result = false))
        val service = SignupService(store, spy)

        val result = service.signUp("grace@example.com", "Grace")

        assertEquals(SignupResult.EMAIL_FAILED, result)
        assertTrue(store.exists("grace@example.com"))
        assertEquals(1, spy.sentMessages.size)
    }
}
