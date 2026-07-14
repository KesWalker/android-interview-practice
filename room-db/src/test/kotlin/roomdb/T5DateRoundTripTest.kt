package roomdb

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.util.Date

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class T5DateRoundTripTest {

    @Test
    fun `a Date survives being stored as a Long and read back`() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val db = buildDatabase(context)
        val when_ = Date(1_700_000_000_000L)

        val stored = recordEvent(db.eventDao(), "launch", when_)

        assertEquals(when_, stored.occurredAt)
    }

    @Test
    fun `different events keep their own timestamps`() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val db = buildDatabase(context)
        val first = Date(1_700_000_000_000L)
        val second = Date(1_800_000_000_000L)

        val storedFirst = recordEvent(db.eventDao(), "first", first)
        val storedSecond = recordEvent(db.eventDao(), "second", second)

        assertEquals(first, storedFirst.occurredAt)
        assertEquals(second, storedSecond.occurredAt)
    }
}
