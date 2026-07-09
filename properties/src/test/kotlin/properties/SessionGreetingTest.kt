package properties

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

// Task 2: lateinit + checking initialization state.
class SessionGreetingTest {
    @Test fun `greets a stranger before username is set`() {
        val session = UserSession()
        assertEquals("Hello, stranger!", session.greeting())
    }

    @Test fun `greets by name once username is set`() {
        val session = UserSession()
        session.username = "Ada"
        assertEquals("Hello, Ada!", session.greeting())
    }

    @Test fun `reading username directly before it's set throws`() {
        val session = UserSession()
        assertThrows(UninitializedPropertyAccessException::class.java) { session.username }
    }
}
