package prefstore

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.reflect.KClass

/**
 * Preferences DataStore practice.
 *
 * Each piece below is broken or unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to
 * implement it so its test goes GREEN. Run a single test class from the
 * gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :preferences-store:test
 */

/** A typed key into a [Preferences] snapshot. */
data class Key<T : Any>(val name: String, val type: KClass<T>)

fun intKey(name: String): Key<Int> = Key(name, Int::class)
fun stringKey(name: String): Key<String> = Key(name, String::class)
fun booleanKey(name: String): Key<Boolean> = Key(name, Boolean::class)

/** An immutable snapshot of stored values, keyed by name. */
data class Preferences(val values: Map<String, Any> = emptyMap())

/** Thrown when a key is read at a type its stored value doesn't match. */
class WrongPreferenceTypeException(message: String) : RuntimeException(message)

// TODO(t1): T1GetOrDefaultTest
// Return the value stored at `key`, or `default` when it's missing. Assume
// any stored value is already the right type.
fun <T : Any> Preferences.getOrDefault(key: Key<T>, default: T): T {
    TODO("t1: return the stored value for key, or default when it's missing")
}

// TODO(t2): T2GetTypedTest
// Return the value stored at `key`, or null when it's missing. If a value
// IS present but isn't an instance of the type `key` expects, throw
// WrongPreferenceTypeException instead of silently handing back the wrong
// type.
fun <T : Any> Preferences.getTyped(key: Key<T>): T? {
    TODO("t2: return the typed value, null when absent, throw on a type mismatch")
}

// TODO(t3): T3MigrateLegacyTest
// Build a Preferences from a legacy flat key/value map: for each key in
// `knownKeys`, if `legacy` has an entry under that key's name AND the
// stored value is an instance of that key's type, carry it over. Entries
// in `legacy` with no matching known key, or whose value is the wrong
// type for its key, are dropped.
fun migrateLegacy(legacy: Map<String, Any>, knownKeys: List<Key<*>>): Preferences {
    TODO("t3: keep only legacy entries that match a known key's name and type")
}

/**
 * A DataStore-alike: holds one [Preferences] snapshot and exposes it as a
 * hot [StateFlow] that emits a new value on every successful edit.
 */
class PreferencesStore(initial: Preferences = Preferences()) {
    private val state = MutableStateFlow(initial)
    val data: StateFlow<Preferences> = state

    // TODO(t4): T4EditTest
    // Atomically apply `transform` to the current snapshot, store the
    // result and return it. Must not lose updates when many coroutines
    // call edit() at the same time: a read-then-write off a stale
    // snapshot is not good enough.
    suspend fun edit(transform: (Preferences) -> Preferences): Preferences {
        TODO("t4: atomically update the store's snapshot and return the new value")
    }

    // TODO(t5): T5ReadWithLegacyMigrationTest
    // The first time this store's data is still empty, migrate `legacy`
    // into it (via migrateLegacy) and store that as the new snapshot.
    // Once the store holds any data at all, whether from a prior
    // migration or a prior edit, leave it alone: never re-run the
    // migration or overwrite existing values.
    suspend fun readWithLegacyMigration(legacy: Map<String, Any>, knownKeys: List<Key<*>>): Preferences {
        TODO("t5: migrate legacy data in only on the first read of an empty store")
    }
}
