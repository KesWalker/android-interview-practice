package serialization

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DecodeLenientProfileTest {

    @Test
    fun `ignores unknown fields instead of throwing`() {
        val json = """{"username":"ada","age":30,"country":"UK"}"""
        assertEquals(Profile("ada", 30), decodeLenientProfile(json))
    }

    @Test
    fun `still decodes json with no extra fields`() {
        val json = """{"username":"grace","age":41}"""
        assertEquals(Profile("grace", 41), decodeLenientProfile(json))
    }
}
