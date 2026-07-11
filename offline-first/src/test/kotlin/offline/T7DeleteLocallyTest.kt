package offline

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class T7DeleteLocallyTest {

    @Test
    fun `deleting a record marks it deleted and bumps its timestamp`() {
        val record = Record(id = "note-1", value = "shopping list", timestamp = 100L)

        val result = deleteLocally(record, deletedAtTimestamp = 200L)

        assertTrue(result.deleted)
        assertEquals(200L, result.timestamp)
    }

    @Test
    fun `deleting a record preserves its id and value`() {
        val record = Record(id = "note-2", value = "todo list", timestamp = 50L)

        val result = deleteLocally(record, deletedAtTimestamp = 75L)

        assertEquals("note-2", result.id)
        assertEquals("todo list", result.value)
    }
}
