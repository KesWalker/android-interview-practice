package repository

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 2: single source of truth - the network never reaches the UI directly.
class T2RefreshArticlesTest {
    @Test fun `returns the mapped domain models`() = runTest {
        val local = LocalArticleStore()
        val dtos = listOf(
            ArticleDto("1", "One", "<p/>", "x"),
            ArticleDto("2", "Two", "<p/>", "y")
        )

        val result = refreshArticles({ dtos }, local)

        assertEquals(listOf(Article("1", "One"), Article("2", "Two")), result)
    }

    @Test fun `writes the mapped models into the local store`() = runTest {
        val local = LocalArticleStore()
        val dtos = listOf(ArticleDto("1", "One", "<p/>", "x"))

        refreshArticles({ dtos }, local)

        assertEquals(listOf(Article("1", "One")), local.observe().first())
    }

    @Test fun `a second refresh replaces the store contents rather than appending`() = runTest {
        val local = LocalArticleStore()

        refreshArticles({ listOf(ArticleDto("1", "One", "<p/>", "x")) }, local)
        refreshArticles({ listOf(ArticleDto("2", "Two", "<p/>", "y")) }, local)

        assertEquals(listOf(Article("2", "Two")), local.observe().first())
    }
}
