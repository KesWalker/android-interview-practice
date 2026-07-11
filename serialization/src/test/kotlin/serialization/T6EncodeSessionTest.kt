package serialization

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class T6EncodeSessionTest {

    @Test
    fun `encoded json contains only the userId key`() {
        val json = encodeSession(Session(userId = "u1", debugToken = "secret"))
        assertEquals("""{"userId":"u1"}""", json)
    }

    @Test
    fun `decoding the encoded json restores the transient field's declared default`() {
        val json = encodeSession(Session(userId = "u1", debugToken = "secret"))
        val decoded = Json.decodeFromString<Session>(json)
        assertEquals("none", decoded.debugToken)
    }
}
