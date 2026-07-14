package datastoreprefs

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class T3AtomicIncrementTest {

    @Test
    fun `increments the key from its current value`() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val store = createStoreAndWriteCount(context, "t3-basic-${System.nanoTime()}", 0)

        incrementAtomically(store)
        incrementAtomically(store)

        assertEquals(2, store.data.first()[COUNT_KEY])
    }

    @Test
    fun `concurrent increments do not lose updates`() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val store = createStoreAndWriteCount(context, "t3-concurrent-${System.nanoTime()}", 0)

        coroutineScope {
            repeat(50) {
                launch(Dispatchers.Default) { incrementAtomically(store) }
            }
        }

        assertEquals(50, store.data.first()[COUNT_KEY])
    }
}
