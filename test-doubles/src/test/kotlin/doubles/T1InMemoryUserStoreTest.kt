package doubles

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class T1InMemoryUserStoreTest {

    @Test
    fun `new store has no users`() {
        val store = InMemoryUserStore()

        assertFalse(store.exists("ada@example.com"))
        assertTrue(store.all().isEmpty())
    }

    @Test
    fun `saved user is found by email`() {
        val store = InMemoryUserStore()
        store.save(User("ada@example.com", "Ada"))

        assertTrue(store.exists("ada@example.com"))
        assertFalse(store.exists("grace@example.com"))
    }

    @Test
    fun `all returns every saved user in order`() {
        val store = InMemoryUserStore()
        store.save(User("ada@example.com", "Ada"))
        store.save(User("grace@example.com", "Grace"))

        assertEquals(
            listOf(User("ada@example.com", "Ada"), User("grace@example.com", "Grace")),
            store.all()
        )
    }
}
