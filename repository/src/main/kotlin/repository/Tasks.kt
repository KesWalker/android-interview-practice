package repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Repository & Data Layer practice.
 *
 * Each function or class below is unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to
 * implement it idiomatically so its test goes GREEN. Run a single test class
 * from the gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :repository:test
 */

// A raw shape as it comes back from a network call - more fields than the
// app actually needs.
data class ArticleDto(val id: String, val title: String, val bodyHtml: String, val internalTag: String)

// The trimmed, immutable model the rest of the app works with.
data class Article(val id: String, val title: String)

// A fake local database: it holds the current list of articles and lets
// callers observe it over time. Already implemented - it's the fixture your
// tasks below work against, not something you need to change.
class LocalArticleStore {
    private val state = MutableStateFlow<List<Article>>(emptyList())

    fun observe(): Flow<List<Article>> = state.asStateFlow()

    fun saveAll(articles: List<Article>) {
        state.value = articles
    }
}

// TODO(t1): FetchArticleTest
// Fetch the raw dto for `id` using `fetchDto`, then return only the fields
// the app needs as an `Article`.
suspend fun fetchArticle(id: String, fetchDto: suspend (String) -> ArticleDto): Article {
    TODO("t1: fetch the dto for id, then return just the fields the app needs")
}

// TODO(t2): RefreshArticlesTest
// Fetch every dto with `fetchAll`, keep only the fields the app needs, store
// that list in `local`, and also return it.
suspend fun refreshArticles(fetchAll: suspend () -> List<ArticleDto>, local: LocalArticleStore): List<Article> {
    TODO("t2: fetch all dtos, trim them down, save them into local, and return them")
}

// TODO(t3): LoadOnDispatcherTest
// Run `blockingWork` using `dispatcher` instead of whatever context called
// this function, and return its result.
suspend fun <T> loadOnDispatcher(dispatcher: CoroutineDispatcher, blockingWork: () -> T): T {
    TODO("t3: run blockingWork using dispatcher and return its result")
}

// TODO(t4): ArticleCacheTest
// A hit counter keyed by article id; many callers may record a hit for the
// same id at the same time and no increment should ever be lost.
class ArticleCache {
    private val hits = mutableMapOf<String, Int>()

    suspend fun recordHit(id: String) {
        TODO("t4: add one hit for id without losing updates under concurrent access")
    }

    suspend fun hitsFor(id: String): Int = hits[id] ?: 0
}
