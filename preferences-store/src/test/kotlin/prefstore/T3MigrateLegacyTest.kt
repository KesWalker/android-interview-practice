package prefstore

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 3: migrating a legacy flat map into typed Preferences.
class T3MigrateLegacyTest {
    private val count = intKey("count")
    private val name = stringKey("name")
    private val knownKeys = listOf(count, name)

    @Test fun `carries over entries that match a known key's name and type`() {
        val legacy = mapOf("count" to 5, "name" to "Ada")
        assertEquals(Preferences(mapOf("count" to 5, "name" to "Ada")), migrateLegacy(legacy, knownKeys))
    }

    @Test fun `drops entries with no matching known key`() {
        val legacy = mapOf("count" to 5, "unrelated" to "junk")
        assertEquals(Preferences(mapOf("count" to 5)), migrateLegacy(legacy, knownKeys))
    }

    @Test fun `drops entries whose value is the wrong type for their key`() {
        val legacy = mapOf("count" to "not a number", "name" to "Ada")
        assertEquals(Preferences(mapOf("name" to "Ada")), migrateLegacy(legacy, knownKeys))
    }

    @Test fun `empty legacy map produces empty preferences`() {
        assertEquals(Preferences(emptyMap()), migrateLegacy(emptyMap(), knownKeys))
    }
}
