package properties

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 4: backing-property pattern (mutable internally, read-only externally).
// You declare `items` from scratch in Tasks.kt; found by name once it exists.
class T4TodoListItemsTest {
    private fun itemsOf(list: TodoList): Any? {
        val getter = list.property("getItems")
            ?: notDeclaredYet(
                "t4: TodoList.items",
                "Declare a val `items: List<String>` in TodoList that exposes the " +
                    "private _items read-only - the backing-property pattern. A getter " +
                    "returning the same underlying list keeps it a live view.",
            )
        return getter.call(list)
    }

    @Test fun `starts empty`() {
        val list = TodoList()
        assertEquals(emptyList<String>(), itemsOf(list))
    }

    @Test fun `reflects items added through the class`() {
        val list = TodoList()
        list.add("bread")
        list.add("milk")
        assertEquals(listOf("bread", "milk"), itemsOf(list))
    }

    @Test fun `items is a live view, not a snapshot copy`() {
        val list = TodoList()
        list.add("a")
        val view = itemsOf(list)
        list.add("b")
        assertEquals(listOf("a", "b"), view)
    }
}
