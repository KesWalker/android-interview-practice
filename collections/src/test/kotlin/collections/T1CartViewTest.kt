package collections

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 1: read-only interfaces are views, not immutable snapshots.
class T1CartViewTest {
    @Test fun `starts empty`() {
        assertEquals(emptyList<String>(), Cart().items)
    }

    @Test fun `view reflects an item added after it was obtained`() {
        val cart = Cart()
        val view = cart.items
        cart.add("apple")
        assertEquals(listOf("apple"), view)
    }

    @Test fun `view keeps reflecting further additions`() {
        val cart = Cart()
        cart.add("apple")
        val view = cart.items
        cart.add("banana")
        assertEquals(listOf("apple", "banana"), view)
    }
}
