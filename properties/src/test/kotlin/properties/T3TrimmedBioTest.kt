package properties

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 3: custom setter that transforms what gets stored. You declare `bio`
// from scratch in Tasks.kt; this test finds it by name once it exists.
class T3TrimmedBioTest {
    private fun Profile.bioProperty() = Pair(
        property("getBio") ?: missing(),
        setter("setBio", String::class.java) ?: missing(),
    )

    private fun missing(): Nothing = notDeclaredYet(
        "t3: Profile.bio",
        "Declare a var `bio: String` in Profile, starting as \"\", whose custom " +
            "setter stores the assigned value trimmed. Inside a setter, `field` is " +
            "the only way to write the backing storage without recursing into the " +
            "setter itself.",
    )

    private fun assign(profile: Profile, text: String) {
        val (_, set) = profile.bioProperty()
        set.call(profile, text)
    }

    private fun read(profile: Profile): Any? {
        val (get, _) = profile.bioProperty()
        return get.call(profile)
    }

    @Test fun `trims leading and trailing whitespace on assignment`() {
        val profile = Profile()
        assign(profile, "  hello  ")
        assertEquals("hello", read(profile))
    }

    @Test fun `leaves already-trimmed text unchanged`() {
        val profile = Profile()
        assign(profile, "hello")
        assertEquals("hello", read(profile))
    }

    @Test fun `re-assigning replaces the previously stored value`() {
        val profile = Profile()
        assign(profile, "first")
        assign(profile, "  second  ")
        assertEquals("second", read(profile))
    }
}
