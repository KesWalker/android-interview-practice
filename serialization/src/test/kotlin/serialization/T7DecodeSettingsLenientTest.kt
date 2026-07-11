package serialization

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class T7DecodeSettingsLenientTest {

    @Test
    fun `an explicit null for fontScale falls back to the default`() {
        assertEquals(Settings(), decodeSettingsLenient("""{"fontScale":null}"""))
    }

    @Test
    fun `a present numeric value is used as-is`() {
        assertEquals(Settings(fontScale = 1.5), decodeSettingsLenient("""{"fontScale":1.5}"""))
    }

    @Test
    fun `a missing key also falls back to the default`() {
        assertEquals(Settings(), decodeSettingsLenient("{}"))
    }
}
