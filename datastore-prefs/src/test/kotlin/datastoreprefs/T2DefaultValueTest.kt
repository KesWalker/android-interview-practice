package datastoreprefs

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class T2DefaultValueTest {

    @Test
    fun `falls back to light when the key was never written`() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val store = createStoreAndWriteCount(context, "t2-missing-${System.nanoTime()}", 0)

        assertEquals("light", readThemeOrDefault(store))
    }

    @Test
    fun `returns the stored value once it has been written`() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val store = createStoreAndWriteCount(context, "t2-present-${System.nanoTime()}", 0)
        store.edit { it[THEME_KEY] = "dark" }

        assertEquals("dark", readThemeOrDefault(store))
    }
}
