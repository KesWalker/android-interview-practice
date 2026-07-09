package clean

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Clean Architecture & Use Cases practice.
 *
 * Each class below is a small piece of a news app's domain layer: pure Kotlin,
 * no Android imports, wired together with fakes in the tests. Each has a
 * matching test in src/test that is currently RED. Your job, one task at a
 * time, is to fill in the body so its test goes GREEN. Run a single test class
 * from the gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :clean-arch:test
 */

data class User(val id: String, val name: String)
data class Article(val id: String, val title: String, val authorId: String)
data class Author(val id: String, val name: String)
data class ArticleWithAuthor(val title: String, val authorName: String)
data class UserFeed(val user: User, val articles: List<Article>)

interface UserRepository {
    suspend fun getUser(id: String): User?
}

interface ArticleRepository {
    suspend fun getArticles(): List<Article>
}

interface AuthorRepository {
    suspend fun getAuthor(id: String): Author?
}

fun interface FetchUser {
    suspend operator fun invoke(id: String): User?
}

fun interface FetchArticles {
    suspend operator fun invoke(): List<Article>
}

// TODO(t1): GetUserUseCaseTest
// WHAT: look up a user by id, or null when no such user exists.
class GetUserUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(id: String): User? {
        TODO("t1: return the user for id from userRepository, or null when missing")
    }
}

// TODO(t2): GetArticlesWithAuthorsUseCaseTest
// WHAT: pair every article with its author's name, leaving out any article
// whose author can't be found.
class GetArticlesWithAuthorsUseCase(
    private val articleRepository: ArticleRepository,
    private val authorRepository: AuthorRepository
) {
    suspend operator fun invoke(): List<ArticleWithAuthor> {
        TODO("t2: combine articles with their authors, skipping any with an unknown author")
    }
}

// TODO(t3): GetUserFeedUseCaseTest
// WHAT: build a user's feed from their profile and the article list, or null
// when the user can't be found.
class GetUserFeedUseCase(
    private val fetchUser: FetchUser,
    private val fetchArticles: FetchArticles
) {
    suspend operator fun invoke(userId: String): UserFeed? {
        TODO("t3: fetch the user and the articles, returning a combined UserFeed, or null if there's no user")
    }
}

// TODO(t4): CalculateTotalUseCaseTest
// WHAT: multiply price by quantity, running the calculation via the supplied
// dispatcher instead of whatever context called it.
class CalculateTotalUseCase(private val dispatcher: CoroutineDispatcher) {
    suspend operator fun invoke(price: Int, quantity: Int): Int {
        TODO("t4: return price * quantity, running the work on dispatcher")
    }
}
