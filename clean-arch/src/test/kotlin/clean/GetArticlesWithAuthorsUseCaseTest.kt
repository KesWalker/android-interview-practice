package clean

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GetArticlesWithAuthorsUseCaseTest {

    private class FakeArticleRepository(private val articles: List<Article>) : ArticleRepository {
        override suspend fun getArticles(): List<Article> = articles
    }

    private class FakeAuthorRepository(private val authors: Map<String, Author>) : AuthorRepository {
        override suspend fun getAuthor(id: String): Author? = authors[id]
    }

    @Test
    fun `pairs each article with its author's name`() = runTest {
        val articles = listOf(
            Article("a1", "Kotlin Coroutines", "au1"),
            Article("a2", "Clean Architecture", "au2")
        )
        val authors = mapOf(
            "au1" to Author("au1", "Grace"),
            "au2" to Author("au2", "Alan")
        )
        val useCase = GetArticlesWithAuthorsUseCase(
            FakeArticleRepository(articles),
            FakeAuthorRepository(authors)
        )

        val result = useCase()

        assertEquals(
            listOf(
                ArticleWithAuthor("Kotlin Coroutines", "Grace"),
                ArticleWithAuthor("Clean Architecture", "Alan")
            ),
            result
        )
    }

    @Test
    fun `drops articles whose author cannot be found`() = runTest {
        val articles = listOf(
            Article("a1", "Kotlin Coroutines", "au1"),
            Article("a2", "Orphan Article", "missing")
        )
        val authors = mapOf("au1" to Author("au1", "Grace"))
        val useCase = GetArticlesWithAuthorsUseCase(
            FakeArticleRepository(articles),
            FakeAuthorRepository(authors)
        )

        val result = useCase()

        assertEquals(listOf(ArticleWithAuthor("Kotlin Coroutines", "Grace")), result)
    }
}
