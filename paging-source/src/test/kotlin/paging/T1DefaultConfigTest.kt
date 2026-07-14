package paging

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 1: initialLoadSize defaults to three pages, prefetchDistance to one.
class T1DefaultConfigTest {
    @Test fun `sizes initial load to three pages`() {
        val config = defaultConfig(pageSize = 20)

        assertEquals(20, config.pageSize)
        assertEquals(60, config.initialLoadSize)
    }

    @Test fun `defaults prefetch distance to one page`() {
        val config = defaultConfig(pageSize = 20)

        assertEquals(20, config.prefetchDistance)
    }

    @Test fun `scales with a different page size`() {
        val config = defaultConfig(pageSize = 5)

        assertEquals(15, config.initialLoadSize)
        assertEquals(5, config.prefetchDistance)
    }
}
