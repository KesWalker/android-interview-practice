package serialization

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class T8CentsSerializerTest {

    @Test
    fun `encodes cents as a two-decimal string`() {
        assertEquals("""{"amount":"12.34"}""", Json.encodeToString(Price(Cents(1234))))
    }

    @Test
    fun `decoding that string back gives an equal price`() {
        val decoded = Json.decodeFromString<Price>("""{"amount":"12.34"}""")
        assertEquals(Price(Cents(1234)), decoded)
    }

    @Test
    fun `a value with a zero cents remainder round-trips as two digits, not one or none`() {
        assertEquals("""{"amount":"5.00"}""", Json.encodeToString(Price(Cents(500))))
        assertEquals(Price(Cents(500)), Json.decodeFromString<Price>("""{"amount":"5.00"}"""))
    }
}
