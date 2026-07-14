package paginglist

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

// Task 2: getRefreshKey, reconstructing a page's own index from anchorPosition.
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class T2RefreshKeyTest {
    private val backend = MovieBackend((0 until 10).map { Movie(it, "Movie $it") })
    private val pagingSource = MoviePagingSource(backend, pageSize = 4)

    private fun pageOf(data: List<Movie>, prevKey: Int?, nextKey: Int?) =
        PagingSource.LoadResult.Page(data, prevKey, nextKey)

    @Test fun `resumes at the page index closest to the anchor`() {
        val page0 = pageOf((0..3).map { Movie(it, "Movie $it") }, prevKey = null, nextKey = 1)
        val page1 = pageOf((4..7).map { Movie(it, "Movie $it") }, prevKey = 0, nextKey = 2)
        val state = PagingState(
            pages = listOf(page0, page1),
            anchorPosition = 5,
            config = PagingConfig(pageSize = 4),
            leadingPlaceholderCount = 0,
        )

        assertEquals(1, pagingSource.getRefreshKey(state))
    }

    @Test fun `resolves to page 0 for an anchor in the first page`() {
        val page0 = pageOf((0..3).map { Movie(it, "Movie $it") }, prevKey = null, nextKey = 1)
        val state = PagingState(
            pages = listOf(page0),
            anchorPosition = 0,
            config = PagingConfig(pageSize = 4),
            leadingPlaceholderCount = 0,
        )

        assertEquals(0, pagingSource.getRefreshKey(state))
    }

    @Test fun `returns null when nothing has loaded yet`() {
        val state = PagingState<Int, Movie>(
            pages = emptyList(),
            anchorPosition = null,
            config = PagingConfig(pageSize = 4),
            leadingPlaceholderCount = 0,
        )

        assertNull(pagingSource.getRefreshKey(state))
    }
}
