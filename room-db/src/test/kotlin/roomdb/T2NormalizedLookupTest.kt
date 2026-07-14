package roomdb

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class T2NormalizedLookupTest {

    @Test
    fun `finds a note when the query has different case and padding`() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val db = buildDatabase(context)
        db.noteDao().insertIgnoring(NoteEntity(title = "grocery list"))

        val found = db.noteDao().findByNormalizedTitle("  Grocery List  ")

        assertEquals("grocery list", found?.title)
    }

    @Test
    fun `returns null when nothing matches after normalizing`() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val db = buildDatabase(context)
        db.noteDao().insertIgnoring(NoteEntity(title = "grocery list"))

        val found = db.noteDao().findByNormalizedTitle("something else")

        assertNull(found)
    }
}
