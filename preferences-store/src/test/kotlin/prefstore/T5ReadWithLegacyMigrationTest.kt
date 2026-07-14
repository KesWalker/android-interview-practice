package prefstore

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 5: legacy data only migrates in on the very first read.
class T5ReadWithLegacyMigrationTest {
    private val count = intKey("count")
    private val name = stringKey("name")
    private val knownKeys = listOf(count, name)

    @Test fun `migrates legacy data into an empty store`() = runTest {
        val store = PreferencesStore()
        val legacy = mapOf("count" to 5, "name" to "Ada")

        val result = store.readWithLegacyMigration(legacy, knownKeys)

        assertEquals(Preferences(mapOf("count" to 5, "name" to "Ada")), result)
        assertEquals(result, store.data.value)
    }

    @Test fun `does not re-migrate once the store already holds migrated data`() = runTest {
        val store = PreferencesStore()
        store.readWithLegacyMigration(mapOf("count" to 5), knownKeys)

        val secondLegacy = mapOf("count" to 999, "name" to "changed")
        val result = store.readWithLegacyMigration(secondLegacy, knownKeys)

        assertEquals(Preferences(mapOf("count" to 5)), result)
    }

    @Test fun `does not overwrite data that already came from an edit`() = runTest {
        val store = PreferencesStore()
        store.edit { prefs -> Preferences(prefs.values + ("count" to 1)) }

        val result = store.readWithLegacyMigration(mapOf("count" to 999), knownKeys)

        assertEquals(1, result.getOrDefault(count, 0))
    }
}
