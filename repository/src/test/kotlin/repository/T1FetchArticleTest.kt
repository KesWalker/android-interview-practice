package repository

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 1: mapping a network DTO down to an immutable domain model.
class T1FetchArticleTest {
    @Test fun `maps id and title from the fetched dto`() = runTest {
        val dto = ArticleDto(id = "42", title = "Kotlin Tips", bodyHtml = "<p>Body</p>", internalTag = "cms-only")

        val article = fetchArticle("42") { dto }

        assertEquals(Article("42", "Kotlin Tips"), article)
    }

    @Test fun `drops the fields the domain model does not need`() = runTest {
        val dto = ArticleDto(id = "7", title = "Coroutines", bodyHtml = "<p>...</p>", internalTag = "secret")

        val article = fetchArticle("7") { dto }

        assertEquals("7", article.id)
        assertEquals("Coroutines", article.title)
    }

    @Test fun `passes the requested id through to the fetch function`() = runTest {
        var seenId: String? = null

        fetchArticle("99") { id ->
            seenId = id
            ArticleDto(id, "Title", "<p/>", "x")
        }

        assertEquals("99", seenId)
    }
}
