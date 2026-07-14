package datastoreprefs

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.IOException

/** A DataStore whose `.data` Flow always fails, to prove safeData recovers from it. */
private class FailingStore(private val error: Throwable) : DataStore<Preferences> {
    override val data: Flow<Preferences> = flow { throw error }
    override suspend fun updateData(transform: suspend (Preferences) -> Preferences): Preferences {
        throw UnsupportedOperationException("not needed for this test")
    }
}

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class T5CatchIOExceptionTest {

    @Test
    fun `falls back to empty preferences on IOException`() = runTest {
        val store = FailingStore(IOException("disk unhappy"))

        val result = safeData(store).first()

        assertEquals(emptyPreferences(), result)
    }

    @Test
    fun `lets a non-IOException keep propagating`() = runTest {
        val store = FailingStore(IllegalStateException("not an IO problem"))

        assertThrows(IllegalStateException::class.java) {
            runBlocking { safeData(store).first() }
        }
    }

    @Test
    fun `passes through real data unaffected`() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val store = createStoreAndWriteCount(context, "t5-passthrough-${System.nanoTime()}", 42)

        val result = safeData(store).first()

        assertEquals(42, result[COUNT_KEY])
    }
}
