package paging

/**
 * Paging3 practice.
 *
 * Each function below is unwritten and has a matching test in src/test that is
 * currently RED. Your job, one task at a time, is to implement it so its test
 * goes GREEN. Run a single test class from the gutter in Android Studio, or
 * run them all with:
 *
 *     ./gradlew :paging-source:test
 */

/** Tuning knobs for a paging screen. */
data class PagingConfig(
    val pageSize: Int,
    val initialLoadSize: Int,
    val prefetchDistance: Int,
)

/** Which direction a load is happening in. */
enum class LoadDirection { REFRESH, APPEND, PREPEND }

/** What the pager asks for: how many items, starting from which key, in which direction. */
data class LoadParams(
    val direction: LoadDirection,
    val key: Int?,
    val loadSize: Int,
)

/** What a load can come back with. */
sealed interface LoadResult<out T> {
    data class Page<T>(val data: List<T>, val prevKey: Int?, val nextKey: Int?) : LoadResult<T>
    data class Error(val throwable: Throwable) : LoadResult<Nothing>
}

/** A page already sitting in memory, described by where it starts and how big it is. */
data class LoadedPage(val startKey: Int, val itemCount: Int)

/** The thing that actually fetches items; may throw if the network/DB call fails. */
fun interface Backend<T> {
    fun fetch(offset: Int, size: Int): List<T>
}

// TODO(t1): T1DefaultConfigTest
// Build a PagingConfig for `pageSize`: initialLoadSize defaults to three
// pages' worth of items, and prefetchDistance defaults to one page.
fun defaultConfig(pageSize: Int): PagingConfig {
    TODO("t1: initialLoadSize = pageSize * 3, prefetchDistance = pageSize")
}

// TODO(t2): T2DeriveKeysTest
// Given a page that starts at item index `start` and holds `itemCount` items
// out of `totalCount` total, work out its prevKey and nextKey. prevKey is
// null once `start` is already 0, otherwise it's `start` (loading the page
// before this one should end exactly where this one begins). nextKey is null
// once this page reaches the end of the data, otherwise it's
// `start + itemCount` (loading the next page should start exactly there).
fun deriveKeys(start: Int, itemCount: Int, totalCount: Int): Pair<Int?, Int?> {
    TODO("t2: prevKey null at start == 0 else start; nextKey null at the end else start + itemCount")
}

// TODO(t3): T3LoadTest
// Load one page for `params` out of a source of `totalCount` items backed by
// `backend`. On REFRESH, start at params.key ?: 0. On APPEND, start at
// params.key (the previous page's nextKey). On PREPEND, the page must END at
// params.key: start at maxOf(0, params.key - params.loadSize) and fetch up to
// params.key items. Clamp the fetch size so it never reads past totalCount.
// Derive prevKey/nextKey with deriveKeys. If backend.fetch throws, catch it
// and return LoadResult.Error with that exception instead of letting it
// propagate.
fun <T> load(params: LoadParams, totalCount: Int, backend: Backend<T>): LoadResult<T> {
    TODO("t3: fetch the right slice for params.direction, wrap prevKey/nextKey, or Error on failure")
}

// TODO(t4): T4PrefetchDistanceTest
// Decide whether scrolling has brought the user within `prefetchDistance`
// items of the end of what's currently loaded, which is the signal to kick
// off the next append load. `lastVisibleIndex` is 0-based; `loadedItemCount`
// is how many items are currently in the list.
fun shouldPrefetchNextPage(lastVisibleIndex: Int, loadedItemCount: Int, prefetchDistance: Int): Boolean {
    TODO("t4: true once lastVisibleIndex is within prefetchDistance items of the end")
}

// TODO(t5): T5GetRefreshKeyTest
// After the list is torn down and rebuilt, work out which key to refresh
// around: find the loaded page whose item range is closest to
// `anchorPosition` (the position the user was scrolled to), and return that
// page's startKey. A position inside a page's range is distance 0 from it.
// Return null when there are no loaded pages at all.
fun getRefreshKey(anchorPosition: Int, loadedPages: List<LoadedPage>): Int? {
    TODO("t5: return the startKey of whichever loadedPages entry is closest to anchorPosition")
}
