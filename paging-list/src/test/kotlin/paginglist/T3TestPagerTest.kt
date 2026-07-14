package paginglist

import androidx.paging.PagingConfig
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

// Task 3: driving a PagingSource end to end with paging-testing's TestPager.
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class T3TestPagerTest {
    @Test fun `walks every page until pagination ends`() = runTest {
        val backend = MovieBackend((0 until 10).map { Movie(it, "Movie $it") })
        val pagingSource = MoviePagingSource(backend, pageSize = 4)

        val items = loadAllMovies(pagingSource, PagingConfig(pageSize = 4))

        assertEquals((0..9).map { Movie(it, "Movie $it") }, items)
    }

    @Test fun `a single short page is still returned in full`() = runTest {
        val backend = MovieBackend((0 until 3).map { Movie(it, "Movie $it") })
        val pagingSource = MoviePagingSource(backend, pageSize = 4)

        val items = loadAllMovies(pagingSource, PagingConfig(pageSize = 4))

        assertEquals((0..2).map { Movie(it, "Movie $it") }, items)
    }
}
