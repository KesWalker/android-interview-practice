package properties

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

// Task 2: lateinit + checking initialization state. You declare `username`
// from scratch in Tasks.kt; this test finds it by name once it exists.
class T2SessionGreetingTest {
    private fun UserSession.usernameProperty() = Pair(
        property("getUsername") ?: missing(),
        setter("setUsername", String::class.java) ?: missing(),
    )

    private fun missing(): Nothing = notDeclaredYet(
        "t2: UserSession.username",
        "Declare a `username: String` var in UserSession that is assigned after " +
            "construction, is NOT nullable (no String?), and throws if read too " +
            "early. One modifier gives you exactly that contract.",
    )

    @Test fun `greets a stranger before username is set`() {
        val session = UserSession()
        session.usernameProperty()
        assertEquals("Hello, stranger!", session.greeting())
    }

    @Test fun `greets by name once username is set`() {
        val session = UserSession()
        val (_, set) = session.usernameProperty()
        set.call(session, "Ada")
        assertEquals("Hello, Ada!", session.greeting())
    }

    @Test fun `reading username directly before it's set throws`() {
        val session = UserSession()
        val (get, _) = session.usernameProperty()
        assertThrows(UninitializedPropertyAccessException::class.java) { get.call(session) }
    }
}
