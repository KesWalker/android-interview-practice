package hoisting

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 1: property delegation via getValue/setValue so `by` works.
class T1DelegateTest {
    @Test fun `reads the initial value through by`() {
        var count by MutableStateLike(0)
        assertEquals(0, count)
    }

    @Test fun `writes through by are visible on the next read`() {
        var count by MutableStateLike(0)
        count = 5
        assertEquals(5, count)
        count = count + 1
        assertEquals(6, count)
    }

    @Test fun `two instances hold independent values`() {
        var a by MutableStateLike("a")
        var b by MutableStateLike("b")
        a = "changed"
        assertEquals("changed", a)
        assertEquals("b", b)
    }
}
