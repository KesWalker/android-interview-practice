package paginglist

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

// Task 5: PagingConfig defaults for this screen.
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class T5PagingConfigTest {
    @Test fun `sizes the initial load to three pages and disables placeholders`() {
        val config = moviePagingConfig(pageSize = 20)

        assertEquals(20, config.pageSize)
        assertEquals(60, config.initialLoadSize)
        assertEquals(10, config.prefetchDistance)
        assertFalse(config.enablePlaceholders)
    }

    @Test fun `scales with a different page size`() {
        val config = moviePagingConfig(pageSize = 8)

        assertEquals(24, config.initialLoadSize)
        assertEquals(4, config.prefetchDistance)
    }
}
