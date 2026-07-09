package concurrentcoll

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

// Task 1: the hashCode/equals contract.
class RoomKeyLookupTest {
    @Test fun `map finds an entry via a separate but equal key`() {
        val map = HashMap<RoomKey, String>()
        map[RoomKey("Alpha", 12)] = "Reception"
        assertEquals("Reception", map[RoomKey("Alpha", 12)])
    }

    @Test fun `a different room number is a different key`() {
        val map = HashMap<RoomKey, String>()
        map[RoomKey("Alpha", 12)] = "Reception"
        assertNull(map[RoomKey("Alpha", 13)])
    }

    @Test fun `equals is false against an unrelated type`() {
        val key = RoomKey("Alpha", 12)
        assertFalse(key.equals("Alpha:12"))
    }

    @Test fun `equal keys share a hashCode`() {
        assertEquals(RoomKey("Alpha", 12).hashCode(), RoomKey("Alpha", 12).hashCode())
    }
}
