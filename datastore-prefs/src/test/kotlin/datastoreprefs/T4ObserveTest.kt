package datastoreprefs

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withTimeout
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class T4ObserveTest {

    @Test
    fun `writes the name`() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val store = createStoreAndWriteCount(context, "t4-basic-${System.nanoTime()}", 0)

        setName(store, "Ada")

        assertEquals("Ada", store.data.map { it[NAME_KEY] }.take(1).toList().first())
    }

    @Test
    fun `data re-emits after a write`() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val store = createStoreAndWriteCount(context, "t4-observe-${System.nanoTime()}", 0)

        val emissions = withTimeout(10_000) {
            val collected = async { store.data.map { it[NAME_KEY] }.take(2).toList() }
            setName(store, "Grace")
            collected.await()
        }

        assertEquals(2, emissions.size)
        assertEquals("Grace", emissions.last())
    }
}
