package clean

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class GetUserFeedUseCaseTest {

    @Test
    fun `combines the user and their articles into a feed`() = runTest {
        val user = User("u1", "Ada")
        val articles = listOf(Article("a1", "Kotlin Coroutines", "u1"))
        val useCase = GetUserFeedUseCase(
            fetchUser = FetchUser { id -> if (id == "u1") user else null },
            fetchArticles = FetchArticles { articles }
        )

        val result = useCase("u1")

        assertEquals(UserFeed(user, articles), result)
    }

    @Test
    fun `returns null when the user cannot be found`() = runTest {
        val useCase = GetUserFeedUseCase(
            fetchUser = FetchUser { null },
            fetchArticles = FetchArticles { listOf(Article("a1", "Unreachable", "u1")) }
        )

        val result = useCase("missing")

        assertNull(result)
    }
}
