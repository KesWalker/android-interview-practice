package collections

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 5: Array's fixed size vs List's genuine growth.
class PackIntoTest {
    @Test fun `contains the array's elements followed by extra`() {
        val result = packInto(arrayOf("a", "b"), "c")
        assertEquals(listOf("a", "b", "c"), result)
    }

    @Test fun `supports further add calls, proving it's a real growable list`() {
        val result = packInto(arrayOf("a"), "b")
        result.add("c")
        assertEquals(listOf("a", "b", "c"), result)
    }

    @Test fun `works for an empty array`() {
        val result = packInto(emptyArray(), "only")
        assertEquals(listOf("only"), result)
    }
}
