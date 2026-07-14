package paginglist

import androidx.paging.PagingSource
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

// Task 1: load() returning LoadResult.Page with prevKey/nextKey derived from the page index.
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class T1LoadPagesTest {
    private val backend = MovieBackend((0 until 10).map { Movie(it, "Movie $it") })
    private val pagingSource = MoviePagingSource(backend, pageSize = 4)

    @Test fun `refresh with a null key starts at page 0`() = runTest {
        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(key = null, loadSize = 4, placeholdersEnabled = false),
        )

        check(result is PagingSource.LoadResult.Page)
        assertEquals((0..3).map { Movie(it, "Movie $it") }, result.data)
        assertNull(result.prevKey)
        assertEquals(1, result.nextKey)
    }

    @Test fun `append continues from the given page index`() = runTest {
        val result = pagingSource.load(
            PagingSource.LoadParams.Append(key = 1, loadSize = 4, placeholdersEnabled = false),
        )

        check(result is PagingSource.LoadResult.Page)
        assertEquals((4..7).map { Movie(it, "Movie $it") }, result.data)
        assertEquals(0, result.prevKey)
        assertEquals(2, result.nextKey)
    }

    @Test fun `nextKey is null once the backend runs out of items`() = runTest {
        val result = pagingSource.load(
            PagingSource.LoadParams.Append(key = 2, loadSize = 4, placeholdersEnabled = false),
        )

        check(result is PagingSource.LoadResult.Page)
        assertEquals((8..9).map { Movie(it, "Movie $it") }, result.data)
        assertEquals(1, result.prevKey)
        assertNull(result.nextKey)
    }

    @Test fun `prevKey is null on the very first page`() = runTest {
        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(key = 0, loadSize = 4, placeholdersEnabled = false),
        )

        check(result is PagingSource.LoadResult.Page)
        assertNull(result.prevKey)
    }
}
