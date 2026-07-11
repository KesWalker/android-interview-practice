package serialization

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class T1EncodeCardTest {

    @Test
    fun `encodes fields in declaration order as compact json`() {
        assertEquals("""{"suit":"Spades","rank":7}""", encodeCard(Card("Spades", 7)))
    }

    @Test
    fun `round trips back to an equal card`() {
        val card = Card("Hearts", 12)
        val decoded = Json.decodeFromString<Card>(encodeCard(card))
        assertEquals(card, decoded)
    }
}
