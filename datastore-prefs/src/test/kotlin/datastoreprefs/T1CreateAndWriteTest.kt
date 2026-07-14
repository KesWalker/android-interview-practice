package datastoreprefs

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class T1CreateAndWriteTest {

    @Test
    fun `writes and reads back a typed key`() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val name = "t1-${System.nanoTime()}"

        val store = createStoreAndWriteCount(context, name, 7)

        assertEquals(7, store.data.first()[COUNT_KEY])
    }

    @Test
    fun `each store name is backed by its own file`() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val storeA = createStoreAndWriteCount(context, "t1-a-${System.nanoTime()}", 1)
        val storeB = createStoreAndWriteCount(context, "t1-b-${System.nanoTime()}", 99)

        assertEquals(1, storeA.data.first()[COUNT_KEY])
        assertEquals(99, storeB.data.first()[COUNT_KEY])
    }
}
