package paging

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 4: the prefetchDistance rule for triggering the next append.
class T4PrefetchDistanceTest {
    @Test fun `does not prefetch while comfortably far from the end`() {
        assertFalse(shouldPrefetchNextPage(lastVisibleIndex = 5, loadedItemCount = 20, prefetchDistance = 5))
    }

    @Test fun `prefetches the instant the visible item enters the prefetch window`() {
        assertTrue(shouldPrefetchNextPage(lastVisibleIndex = 14, loadedItemCount = 20, prefetchDistance = 5))
    }

    @Test fun `keeps prefetching all the way to the very last loaded item`() {
        assertTrue(shouldPrefetchNextPage(lastVisibleIndex = 19, loadedItemCount = 20, prefetchDistance = 5))
    }

    @Test fun `with zero prefetch distance only the last item itself triggers it`() {
        assertFalse(shouldPrefetchNextPage(lastVisibleIndex = 18, loadedItemCount = 20, prefetchDistance = 0))
        assertTrue(shouldPrefetchNextPage(lastVisibleIndex = 19, loadedItemCount = 20, prefetchDistance = 0))
    }
}
