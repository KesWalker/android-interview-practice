package nullsafety

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

// Task 4: safe cast `as?`.
class T4ShoutTest {
    @Test fun `uppercases a string`() = assertEquals("HI", shout("hi"))
    @Test fun `non-string is null`() = assertNull(shout(42))
    @Test fun `null is null`() = assertNull(shout(null))
}
