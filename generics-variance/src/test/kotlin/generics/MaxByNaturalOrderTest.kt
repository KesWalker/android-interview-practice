package generics

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 5: a type parameter with two upper bounds via a where clause.
class MaxByNaturalOrderTest {
    @Test fun `returns the greater of two out-of-order strings`() =
        assertEquals("banana", maxByNaturalOrder("apple", "banana"))

    @Test fun `returns the greater regardless of argument order`() =
        assertEquals("banana", maxByNaturalOrder("banana", "apple"))

    @Test fun `equal values return that same value`() =
        assertEquals("kiwi", maxByNaturalOrder("kiwi", "kiwi"))
}
