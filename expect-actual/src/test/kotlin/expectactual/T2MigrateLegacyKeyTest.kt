package expectactual

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

// Task 2: multi-step shared logic, still written only against PlatformStore.
class T2MigrateLegacyKeyTest {
    @Test fun `moves the value from oldKey to newKey and deletes oldKey`() {
        val store = AndroidStore(mutableMapOf("legacy_id" to "abc123"))

        val result = migrateLegacyKey(store, oldKey = "legacy_id", newKey = "user_id")

        assertEquals("abc123", result)
        assertEquals("abc123", store.read("user_id"))
        assertNull(store.read("legacy_id"))
    }

    @Test fun `newKey already present and oldKey absent is left alone`() {
        val store = IosStore(mutableMapOf("user_id" to "already-there"))

        val result = migrateLegacyKey(store, oldKey = "legacy_id", newKey = "user_id")

        assertEquals("already-there", result)
        assertNull(store.read("legacy_id"))
    }

    @Test fun `both keys absent returns null`() {
        val store = AndroidStore()

        assertNull(migrateLegacyKey(store, oldKey = "legacy_id", newKey = "user_id"))
    }

    @Test fun `behaves the same for both actuals`() {
        val android = AndroidStore(mutableMapOf("legacy_id" to "xyz"))
        val ios = IosStore(mutableMapOf("legacy_id" to "xyz"))

        assertEquals("xyz", migrateLegacyKey(android, "legacy_id", "user_id"))
        assertEquals("xyz", migrateLegacyKey(ios, "legacy_id", "user_id"))
    }
}
