package datastoreprefs

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStoreFile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import java.io.IOException

/**
 * DataStore Preferences practice.
 *
 * Each function below is broken or unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to
 * implement it so its test goes GREEN.
 */

val COUNT_KEY = intPreferencesKey("count")
val THEME_KEY = stringPreferencesKey("theme")
val NAME_KEY = stringPreferencesKey("name")

// TODO(t1): T1CreateAndWriteTest
// Build a Preferences DataStore backed by a file called `name` inside this
// app's files directory (the standard PreferenceDataStoreFactory.create +
// preferencesDataStoreFile boilerplate), then write `value` into it under
// COUNT_KEY.
suspend fun createStoreAndWriteCount(context: Context, name: String, value: Int): DataStore<Preferences> {
    TODO(
        "t1: val store = PreferenceDataStoreFactory.create { context.preferencesDataStoreFile(name) }; " +
            "store.edit { it[COUNT_KEY] = value }; return store"
    )
}

// TODO(t2): T2DefaultValueTest
// THEME_KEY might not have been written yet. Read it, falling back to
// "light" when it's missing, the way every settings screen has to.
suspend fun readThemeOrDefault(store: DataStore<Preferences>): String {
    TODO("t2: return store.data.first()[THEME_KEY] ?: \"light\"")
}

// TODO(t3): T3AtomicIncrementTest
// edit {} reads and writes in one atomic transaction: DataStore serializes
// every edit {} block against the same file, so two increments that race
// each other still both land, unlike a naive "read the value, then write
// value + 1" which can silently drop an update if two callers read the same
// stale value before either writes back.
suspend fun incrementAtomically(store: DataStore<Preferences>) {
    TODO("t3: store.edit { it[COUNT_KEY] = (it[COUNT_KEY] ?: 0) + 1 }")
}

// TODO(t4): T4ObserveTest
// Just write. store.data is already a Flow that re-emits on every
// successful write, whoever is collecting it downstream sees this update
// for free, no extra plumbing needed.
suspend fun setName(store: DataStore<Preferences>, value: String) {
    TODO("t4: store.edit { it[NAME_KEY] = value }")
}

// TODO(t5): T5CatchIOExceptionTest
// A real DataStore's Flow can throw IOException if the underlying file
// can't be read. The standard recipe is to catch it and fall back to an
// empty Preferences rather than crash the screen; anything that isn't an
// IOException should keep propagating.
fun safeData(store: DataStore<Preferences>): Flow<Preferences> {
    TODO(
        "t5: return store.data.catch { e -> if (e is IOException) emit(emptyPreferences()) else throw e }"
    )
}

// TODO(t6): T6MigrateFromSharedPreferencesTest
// Build a DataStore backed by dataStoreName that migrates any existing
// values out of the legacy SharedPreferences file sharedPrefsName the first
// time it's opened, using the library's own SharedPreferencesMigration.
fun createStoreMigratingFrom(
    context: Context,
    dataStoreName: String,
    sharedPrefsName: String
): DataStore<Preferences> {
    TODO(
        "t6: return PreferenceDataStoreFactory.create(" +
            "migrations = listOf(SharedPreferencesMigration(context, sharedPrefsName)), " +
            "produceFile = { context.preferencesDataStoreFile(dataStoreName) })"
    )
}
