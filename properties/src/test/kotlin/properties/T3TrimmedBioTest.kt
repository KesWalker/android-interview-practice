package properties

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 3: custom setter that transforms what gets stored.
class T3TrimmedBioTest {
    @Test fun `trims leading and trailing whitespace on assignment`() {
        val profile = Profile()
        profile.bio = "  hello  "
        assertEquals("hello", profile.bio)
    }

    @Test fun `leaves already-trimmed text unchanged`() {
        val profile = Profile()
        profile.bio = "hello"
        assertEquals("hello", profile.bio)
    }

    @Test fun `re-assigning replaces the previously stored value`() {
        val profile = Profile()
        profile.bio = "first"
        profile.bio = "  second  "
        assertEquals("second", profile.bio)
    }
}
