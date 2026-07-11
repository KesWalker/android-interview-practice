package serialization

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DecodeAccountTest {

    @Test
    fun `decodes json using the canonical username key`() {
        assertEquals(Account("ada"), decodeAccount("""{"username":"ada"}"""))
    }

    @Test
    fun `decodes json using the legacy login key`() {
        assertEquals(Account("ada"), decodeAccount("""{"login":"ada"}"""))
    }

    @Test
    fun `decodes json using the legacy user_name key`() {
        assertEquals(Account("ada"), decodeAccount("""{"user_name":"ada"}"""))
    }
}
