package prefstore

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 4: atomic read-modify-write, safe under concurrent edits.
class T4EditTest {
    private val count = intKey("count")

    @Test fun `applies a single edit and returns the new snapshot`() = runTest {
        val store = PreferencesStore()
        val result = store.edit { prefs -> Preferences(prefs.values + ("count" to 1)) }
        assertEquals(1, result.getOrDefault(count, 0))
        assertEquals(1, store.data.value.getOrDefault(count, 0))
    }

    @Test fun `sequential edits each build on the last`() = runTest {
        val store = PreferencesStore()
        repeat(5) {
            store.edit { prefs -> Preferences(prefs.values + ("count" to (prefs.getOrDefault(count, 0) + 1))) }
        }
        assertEquals(5, store.data.value.getOrDefault(count, 0))
    }

    @Test fun `survives many concurrent increments without losing updates`() = runTest {
        val store = PreferencesStore()
        val jobs = List(50) {
            launch(Dispatchers.Default) {
                repeat(1000) {
                    store.edit { prefs -> Preferences(prefs.values + ("count" to (prefs.getOrDefault(count, 0) + 1))) }
                }
            }
        }
        jobs.forEach { it.join() }

        assertEquals(50_000, store.data.value.getOrDefault(count, 0))
    }
}
