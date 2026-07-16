package extensions

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 1: extension function on a nullable receiver. You declare it from
// scratch in Tasks.kt; this test finds it by name once it exists.
class T1DisplayNameTest {
    private fun displayNameOrUnknown(receiver: String?): Any? {
        val m = findExtension("displayNameOrUnknown", String::class.java)
            ?: notDeclaredYet(
                "t1: displayNameOrUnknown",
                "Declare a top-level extension function on a NULLABLE String receiver, " +
                    "taking no arguments and returning String. The nullable receiver is " +
                    "the point: it must be callable directly on null.",
            )
        return m.call(receiver)
    }

    @Test fun `null becomes Unknown`() = assertEquals("Unknown", displayNameOrUnknown(null))
    @Test fun `blank becomes Unknown`() = assertEquals("Unknown", displayNameOrUnknown("   "))
    @Test fun `trims a real name`() = assertEquals("Kes Walker", displayNameOrUnknown("  Kes Walker  "))
}
