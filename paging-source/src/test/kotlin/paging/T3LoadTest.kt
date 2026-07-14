package paging

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.Test

// Task 3: the PagingSource-alike -- Refresh, Append, Prepend and Error.
class T3LoadTest {
    private val backend = Backend<Int> { offset, size -> (offset until offset + size).toList() }

    @Test fun `refresh with no key starts at the beginning`() {
        val result = load(LoadParams(LoadDirection.REFRESH, key = null, loadSize = 3), totalCount = 10, backend)

        val page = result as? LoadResult.Page ?: fail("expected a Page, got $result")
        assertEquals(listOf(0, 1, 2), page.data)
        assertNull(page.prevKey)
        assertEquals(3, page.nextKey)
    }

    @Test fun `append continues from the given key and clamps to what is left`() {
        val result = load(LoadParams(LoadDirection.APPEND, key = 8, loadSize = 3), totalCount = 10, backend)

        val page = result as? LoadResult.Page ?: fail("expected a Page, got $result")
        assertEquals(listOf(8, 9), page.data)
        assertEquals(8, page.prevKey)
        assertNull(page.nextKey)
    }

    @Test fun `prepend loads backward and ends exactly at the given key`() {
        val result = load(LoadParams(LoadDirection.PREPEND, key = 2, loadSize = 3), totalCount = 10, backend)

        val page = result as? LoadResult.Page ?: fail("expected a Page, got $result")
        assertEquals(listOf(0, 1), page.data)
        assertNull(page.prevKey)
        assertEquals(2, page.nextKey)
    }

    @Test fun `wraps a backend failure as an Error instead of throwing`() {
        val failingBackend = Backend<Int> { _, _ -> throw RuntimeException("network down") }

        val result = load(LoadParams(LoadDirection.REFRESH, key = null, loadSize = 3), totalCount = 10, failingBackend)

        val error = result as? LoadResult.Error ?: fail("expected an Error, got $result")
        assertTrue(error.throwable.message == "network down")
    }
}
