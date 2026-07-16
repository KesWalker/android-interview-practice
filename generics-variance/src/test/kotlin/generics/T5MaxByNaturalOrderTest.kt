package generics

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 5: a type parameter with two upper bounds via a where clause. You
// declare the whole generic function yourself in Tasks.kt; the double bound
// itself can't be seen through erasure, so your pair confirms it on screen.
class T5MaxByNaturalOrderTest {
    private fun maxByNaturalOrder(a: String, b: String): Any? {
        val m = findDeclaration("maxByNaturalOrder", 2)
            ?: notDeclaredYet(
                "t5: maxByNaturalOrder",
                "Declare a top-level generic function taking two values of a type " +
                    "parameter T and returning the greater one. T must be constrained " +
                    "to BOTH CharSequence and Comparable<T> - one bound fits inline " +
                    "after <T : ...>, but two bounds need the where clause.",
            )
        return m.call(a, b)
    }

    @Test fun `returns the greater of two out-of-order strings`() =
        assertEquals("banana", maxByNaturalOrder("apple", "banana"))

    @Test fun `returns the greater regardless of argument order`() =
        assertEquals("banana", maxByNaturalOrder("banana", "apple"))

    @Test fun `equal values return that same value`() =
        assertEquals("kiwi", maxByNaturalOrder("kiwi", "kiwi"))
}
