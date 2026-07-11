package collections

import kotlinx.collections.immutable.PersistentList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotSame
import org.junit.jupiter.api.Test

// Task 6: genuinely immutable persistent collections vs a read-only view.
class T6FrozenListTest {
    @Test fun `contains the same elements as items`() {
        val result: PersistentList<String> = frozen(listOf("a", "b", "c"))
        assertEquals(listOf("a", "b", "c"), result)
    }

    @Test fun `an empty source produces an empty persistent list`() {
        assertEquals(emptyList<String>(), frozen(emptyList()))
    }

    @Test fun `mutating operations return a new list instead of changing this one`() {
        val result = frozen(listOf("a", "b"))
        val added = result.add("c")
        assertNotSame(result, added)
        assertEquals(listOf("a", "b"), result)
        assertEquals(listOf("a", "b", "c"), added)
    }
}
