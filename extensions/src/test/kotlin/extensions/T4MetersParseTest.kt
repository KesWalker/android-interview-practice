package extensions

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

// Task 4: extension function on a companion object, so the call site reads
// like a factory on the type itself: Meters.parse("5.5m").
class T4MetersParseTest {
    private fun parse(raw: String): Meters? {
        val m = findExtension("parse", Meters.Companion::class.java, String::class.java)
            ?: notDeclaredYet(
                "t4: Meters.parse",
                "Declare a top-level extension function whose receiver is Meters's " +
                    "COMPANION object (Meters.Companion), taking the raw String and " +
                    "returning Meters?. That receiver is what lets the call site say " +
                    "Meters.parse(...) with no instance in hand.",
            )
        return m.call(Meters.Companion, raw) as Meters?
    }

    @Test fun `parses valid input`() {
        val result = parse("5.5m")
        assertEquals(5.5, result?.value)
    }

    @Test fun `returns null for missing suffix`() = assertNull(parse("5.5"))

    @Test fun `returns null for non numeric value`() = assertNull(parse("abcm"))
}
