package serialization

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DecodePreferencesTest {

    @Test
    fun `fills in missing fields with the model's own defaults`() {
        val prefs = decodePreferences("""{"theme":"dark"}""")
        assertEquals(Preferences(theme = "dark", notificationsEnabled = true), prefs)
    }

    @Test
    fun `explicit values override defaults`() {
        val prefs = decodePreferences("""{"theme":"dark","notificationsEnabled":false}""")
        assertEquals(Preferences("dark", false), prefs)
    }

    @Test
    fun `empty json uses every default`() {
        assertEquals(Preferences(), decodePreferences("{}"))
    }
}
