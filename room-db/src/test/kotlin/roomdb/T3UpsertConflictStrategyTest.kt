package roomdb

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class T3UpsertConflictStrategyTest {

    @Test
    fun `keepExisting true leaves the original row untouched`() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val db = buildDatabase(context)
        val id = db.noteDao().insertIgnoring(NoteEntity(title = "original")).toInt()

        db.noteDao().upsert(NoteEntity(id = id, title = "new"), keepExisting = true)

        assertEquals("original", db.noteDao().findById(id)?.title)
    }

    @Test
    fun `keepExisting false overwrites the original row`() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val db = buildDatabase(context)
        val id = db.noteDao().insertIgnoring(NoteEntity(title = "original")).toInt()

        db.noteDao().upsert(NoteEntity(id = id, title = "new"), keepExisting = false)

        assertEquals("new", db.noteDao().findById(id)?.title)
    }
}
