package clean

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class T5GetUserNewsUseCaseTest {

    private class FakeUserRepository(private val users: Map<String, User>) : UserRepository {
        override suspend fun getUser(id: String): User? = users[id]
    }

    private class FakeArticleRepository(private val articles: List<Article>) : ArticleRepository {
        override suspend fun getArticles(): List<Article> = articles
    }

    private class FailingArticleRepository : ArticleRepository {
        override suspend fun getArticles(): List<Article> =
            error("news use case should not be called for an unknown user")
    }

    @Test
    fun `combines the user and the latest news for a known user`() = runTest {
        val user = User("u1", "Ada")
        val news = listOf(Article("a1", "Kotlin Coroutines", "u1"))
        val useCase = GetUserNewsUseCase(
            GetUserUseCase(FakeUserRepository(mapOf("u1" to user))),
            GetLatestNewsUseCase(FakeArticleRepository(news))
        )

        val result = useCase("u1")

        assertEquals(UserNews(user, news), result)
    }

    @Test
    fun `returns null for an unknown user without needing the news use case's result`() = runTest {
        val useCase = GetUserNewsUseCase(
            GetUserUseCase(FakeUserRepository(emptyMap())),
            GetLatestNewsUseCase(FailingArticleRepository())
        )

        val result = useCase("missing")

        assertNull(result)
    }
}
