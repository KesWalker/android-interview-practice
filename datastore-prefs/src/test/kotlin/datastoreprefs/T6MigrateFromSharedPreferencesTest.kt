package datastoreprefs

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class T6MigrateFromSharedPreferencesTest {

    @Test
    fun `pulls an existing SharedPreferences value into the DataStore`() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val suffix = System.nanoTime()
        val sharedPrefsName = "legacy-prefs-$suffix"
        val dataStoreName = "migrated-$suffix"

        context.getSharedPreferences(sharedPrefsName, Context.MODE_PRIVATE)
            .edit()
            .putString("name", "kes")
            .commit()

        val store = createStoreMigratingFrom(context, dataStoreName, sharedPrefsName)

        assertEquals("kes", store.data.first()[NAME_KEY])
    }

    @Test
    fun `leaves the key unset when the SharedPreferences file was empty`() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val suffix = System.nanoTime()
        val sharedPrefsName = "empty-legacy-prefs-$suffix"
        val dataStoreName = "migrated-empty-$suffix"

        val store = createStoreMigratingFrom(context, dataStoreName, sharedPrefsName)

        assertNull(store.data.first()[NAME_KEY])
    }
}
