package properties

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 4: backing-property pattern (mutable internally, read-only externally).
class T4TodoListItemsTest {
    @Test fun `starts empty`() {
        val list = TodoList()
        assertEquals(emptyList<String>(), list.items)
    }

    @Test fun `reflects items added through the class`() {
        val list = TodoList()
        list.add("bread")
        list.add("milk")
        assertEquals(listOf("bread", "milk"), list.items)
    }

    @Test fun `items is a live view, not a snapshot copy`() {
        val list = TodoList()
        list.add("a")
        val view = list.items
        list.add("b")
        assertEquals(listOf("a", "b"), view)
    }
}
