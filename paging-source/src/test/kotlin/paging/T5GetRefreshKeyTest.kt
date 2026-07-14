package paging

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

// Task 5: getRefreshKey -- pick the loaded page closest to the anchor position.
class T5GetRefreshKeyTest {
    private val loadedPages = listOf(
        LoadedPage(startKey = 0, itemCount = 10),
        LoadedPage(startKey = 10, itemCount = 10),
        LoadedPage(startKey = 30, itemCount = 10),
    )

    @Test fun `an anchor inside a loaded page refreshes around that page`() {
        assertEquals(10, getRefreshKey(anchorPosition = 15, loadedPages))
    }

    @Test fun `an anchor between two pages picks whichever one is nearer`() {
        assertEquals(10, getRefreshKey(anchorPosition = 24, loadedPages))
        assertEquals(30, getRefreshKey(anchorPosition = 27, loadedPages))
    }

    @Test fun `an anchor past every loaded page falls back to the last one`() {
        assertEquals(30, getRefreshKey(anchorPosition = 100, loadedPages))
    }

    @Test fun `no loaded pages means no refresh key`() {
        assertNull(getRefreshKey(anchorPosition = 15, emptyList()))
    }
}
