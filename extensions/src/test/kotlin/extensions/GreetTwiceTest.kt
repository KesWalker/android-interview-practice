package extensions

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 6: a member function always wins over an extension of the same
// signature, so the extension greet() below is never actually reachable.
class GreetTwiceTest {
    @Test fun `member greet wins over the extension`() {
        assertEquals("membermember", greetTwice(Greeter()))
    }
}
