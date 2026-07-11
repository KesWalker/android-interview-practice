package mvvm

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

// Task 6: a one-off navigation target modeled as consume-once state.
class ItemListHolderTest {
    @Test fun `starts with no pending navigation`() {
        val holder = ItemListHolder()
        assertNull(holder.state.value.navigateTo)
    }

    @Test fun `clicking an item sets navigateTo to its id`() {
        val holder = ItemListHolder()
        holder.onItemClicked("item-1")
        assertEquals("item-1", holder.state.value.navigateTo)
    }

    @Test fun `onNavigated clears the pending navigation back to null`() {
        val holder = ItemListHolder()
        holder.onItemClicked("item-1")
        holder.onNavigated()
        assertNull(holder.state.value.navigateTo)
    }

    @Test fun `clicking a second item after consuming the first re-fires with the new id`() {
        val holder = ItemListHolder()
        holder.onItemClicked("item-1")
        holder.onNavigated()
        holder.onItemClicked("item-2")
        assertEquals("item-2", holder.state.value.navigateTo)
    }
}
