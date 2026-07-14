package paginglist

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadParams
import androidx.paging.PagingSource.LoadResult
import androidx.paging.PagingState
import androidx.paging.testing.TestPager
import java.io.IOException

/**
 * Paging 3 practice, against the real androidx Paging library.
 *
 * You're paging through a catalog of movies, pageSize at a time. Each
 * function or class below is unwritten and has a matching test in src/test
 * that is currently RED. Your job, one task at a time, is to implement it so
 * its test goes GREEN. Run a single test class from the gutter in Android
 * Studio, or run them all with:
 *
 *     ./gradlew :paging-list:test
 */

data class Movie(val id: Int, val title: String)

/** A fake backend: `offset` is the item index to start at, `limit` how many to fetch. */
class MovieBackend(private val all: List<Movie>) {
    fun fetchPage(offset: Int, limit: Int): List<Movie> {
        if (offset >= all.size) return emptyList()
        return all.subList(offset, minOf(offset + limit, all.size))
    }
}

class MoviePagingSource(
    private val backend: MovieBackend,
    private val pageSize: Int = 4,
) : PagingSource<Int, Movie>() {

    // TODO(t1): T1LoadPagesTest
    // params.key is the page index to load (0 when refreshing with a null
    // key). Fetch pageSize items starting at pageIndex * pageSize. prevKey is
    // null on page 0, otherwise pageIndex - 1. nextKey is null once the
    // backend returns fewer than pageSize items (nothing left to fetch),
    // otherwise pageIndex + 1.
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        TODO("t1: fetch this page, then wrap it as LoadResult.Page with prevKey/nextKey derived from pageIndex")
    }

    // TODO(t2): T2RefreshKeyTest
    // Use state.anchorPosition to find the loaded page closest to where the
    // user was scrolled (state.closestPageToPosition), then reconstruct that
    // page's own pageIndex from its prevKey/nextKey: prevKey + 1, or if this
    // is the first page (prevKey null), nextKey - 1. Return null if nothing
    // has loaded yet.
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        TODO("t2: closestPageToPosition(anchorPosition), then page.prevKey?.plus(1) ?: page.nextKey?.minus(1)")
    }
}

// TODO(t3): T3TestPagerTest
// Walk every page of `pagingSource` using a TestPager: refresh() first (as
// Paging always does), then keep calling append() and collecting each
// page's data until append() returns null, which is TestPager's signal that
// there's nothing left to load. Return every item collected, in order.
suspend fun loadAllMovies(pagingSource: PagingSource<Int, Movie>, config: PagingConfig): List<Movie> {
    TODO("t3: TestPager(config, pagingSource); refresh() then append() in a loop until it returns null")
}

/** A backend that fails its very first call, then works normally after. */
class FlakyBackend(private val all: List<Movie>) {
    private var failNext = true

    fun fetchPage(offset: Int, limit: Int): List<Movie> {
        if (failNext) {
            failNext = false
            throw IOException("network blip")
        }
        if (offset >= all.size) return emptyList()
        return all.subList(offset, minOf(offset + limit, all.size))
    }
}

class FlakyMoviePagingSource(
    private val backend: FlakyBackend,
    private val pageSize: Int = 4,
) : PagingSource<Int, Movie>() {

    // TODO(t4): T4LoadErrorTest
    // Same page-fetching logic as MoviePagingSource.load, but backend.fetchPage
    // can throw. Catch the failure and return LoadResult.Error with it instead
    // of letting the exception propagate out of load(). A later call (once the
    // backend has recovered) should succeed normally.
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        TODO("t4: wrap the fetch in try/catch, returning LoadResult.Error(e) on failure")
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? = null
}

// TODO(t5): T5PagingConfigTest
// Build a PagingConfig for a screen that pages pageSize items at a time:
// warm up with three pages' worth on the first load (initialLoadSize =
// pageSize * 3), start the next append halfway through the current page
// (prefetchDistance = pageSize / 2), and turn placeholders off, since this
// backend doesn't expose a total item count.
fun moviePagingConfig(pageSize: Int): PagingConfig {
    TODO("t5: PagingConfig(pageSize, initialLoadSize = pageSize * 3, prefetchDistance = pageSize / 2, enablePlaceholders = false)")
}
