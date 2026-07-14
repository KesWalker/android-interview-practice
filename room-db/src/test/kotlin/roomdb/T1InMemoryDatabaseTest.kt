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
class T1InMemoryDatabaseTest {

    @Test
    fun `round trips a note through an in-memory database`() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val db = buildDatabase(context)

        db.noteDao().insertIgnoring(NoteEntity(title = "milk"))
        val found = db.noteDao().findByTitle("milk")

        assertEquals("milk", found?.title)
    }

    @Test
    fun `each call builds its own isolated database`() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val dbA = buildDatabase(context)
        val dbB = buildDatabase(context)

        dbA.noteDao().insertIgnoring(NoteEntity(title = "only in a"))

        assertNull(dbB.noteDao().findByTitle("only in a"))
        assertEquals("only in a", dbA.noteDao().findByTitle("only in a")?.title)
    }
}
