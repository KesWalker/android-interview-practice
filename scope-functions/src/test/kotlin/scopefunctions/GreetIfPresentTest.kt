package scopefunctions

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

// Task 1: let + null safety.
class GreetIfPresentTest {
    @Test fun `null becomes null`() = assertNull(greetIfPresent(null))
    @Test fun `blank becomes null`() = assertNull(greetIfPresent("   "))
    @Test fun `greets by name`() = assertEquals("Hello, Ana!", greetIfPresent("Ana"))
}
