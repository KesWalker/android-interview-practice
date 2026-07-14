package roomdb

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class T4RenameNoteTest {

    @Test
    fun `renaming updates the stored title`() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val db = buildDatabase(context)
        val id = db.noteDao().insertIgnoring(NoteEntity(title = "old")).toInt()

        renameNote(db.noteDao(), id, "new")

        assertEquals("new", db.noteDao().findById(id)?.title)
    }

    @Test
    fun `observeAll re-emits after a rename`() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val db = buildDatabase(context)
        val id = db.noteDao().insertIgnoring(NoteEntity(title = "old")).toInt()

        // Room's Flow re-emission runs on its own background executor, not
        // this test's virtual dispatcher, so this part of the test needs a
        // real thread. limitedParallelism(1) keeps it single-threaded and
        // deterministic: we explicitly wait for the collector's FIRST
        // emission (proof it's actually subscribed) before renaming, then
        // wait for the SECOND emission (proof the rename triggered a
        // re-query), instead of racing the two.
        val secondEmission = withContext(Dispatchers.Default.limitedParallelism(1)) {
            withTimeout(10_000) {
                val firstEmission = CompletableDeferred<Unit>()
                val secondEmission = CompletableDeferred<List<NoteEntity>>()
                var emissionCount = 0
                val job = launch {
                    db.noteDao().observeAll().collect { notes ->
                        emissionCount++
                        if (emissionCount == 1) firstEmission.complete(Unit)
                        if (emissionCount == 2) secondEmission.complete(notes)
                    }
                }

                firstEmission.await()
                renameNote(db.noteDao(), id, "new")
                val result = secondEmission.await()
                job.cancel()
                result
            }
        }

        assertEquals("new", secondEmission.first { it.id == id }.title)
    }
}
