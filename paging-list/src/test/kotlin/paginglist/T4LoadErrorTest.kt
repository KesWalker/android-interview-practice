package paginglist

import androidx.paging.PagingSource
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

// Task 4: a failing fetch becomes LoadResult.Error instead of an exception, and a retry can succeed.
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class T4LoadErrorTest {
    @Test fun `a failing fetch becomes a LoadResult Error instead of throwing`() = runTest {
        val backend = FlakyBackend((0 until 4).map { Movie(it, "Movie $it") })
        val pagingSource = FlakyMoviePagingSource(backend, pageSize = 4)

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(key = null, loadSize = 4, placeholdersEnabled = false),
        )

        assertTrue(result is PagingSource.LoadResult.Error)
    }

    @Test fun `retrying the same load after the backend recovers succeeds`() = runTest {
        val backend = FlakyBackend((0 until 4).map { Movie(it, "Movie $it") })
        val pagingSource = FlakyMoviePagingSource(backend, pageSize = 4)

        val failed = pagingSource.load(
            PagingSource.LoadParams.Refresh(key = null, loadSize = 4, placeholdersEnabled = false),
        )
        assertTrue(failed is PagingSource.LoadResult.Error)

        val retried = pagingSource.load(
            PagingSource.LoadParams.Refresh(key = null, loadSize = 4, placeholdersEnabled = false),
        )
        check(retried is PagingSource.LoadResult.Page)
        assertEquals((0..3).map { Movie(it, "Movie $it") }, retried.data)
    }
}
