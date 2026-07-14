package roomdb

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class T6AuthorRelationTest {

    @Test
    fun `collects every book title for that author`() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val db = buildDatabase(context)
        val dao = db.authorBookDao()
        val authorId = dao.insertAuthor(AuthorEntity(name = "Ursula")).toInt()

        addBookToAuthor(dao, authorId, "Book One")
        val titles = addBookToAuthor(dao, authorId, "Book Two")

        assertEquals(setOf("Book One", "Book Two"), titles.toSet())
    }

    @Test
    fun `does not mix in another author's books`() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val db = buildDatabase(context)
        val dao = db.authorBookDao()
        val authorAId = dao.insertAuthor(AuthorEntity(name = "Author A")).toInt()
        val authorBId = dao.insertAuthor(AuthorEntity(name = "Author B")).toInt()

        addBookToAuthor(dao, authorBId, "B's Book")
        val titlesForA = addBookToAuthor(dao, authorAId, "A's Book")

        assertEquals(listOf("A's Book"), titlesForA)
        assertTrue(authorAId != authorBId)
    }
}
