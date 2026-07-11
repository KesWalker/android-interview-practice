package collections

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 3: split a collection into two lists in a single pass.
class T3SplitByLengthTest {
    @Test fun `splits long and short words, keeping order`() {
        val (long, short) = splitByLength(listOf("a", "kotlin", "hi", "android"), 3)
        assertEquals(listOf("kotlin", "android"), long)
        assertEquals(listOf("a", "hi"), short)
    }

    @Test fun `all words qualify`() {
        val (long, short) = splitByLength(listOf("kotlin", "android"), 3)
        assertEquals(listOf("kotlin", "android"), long)
        assertEquals(emptyList<String>(), short)
    }

    @Test fun `no words qualify`() {
        val (long, short) = splitByLength(listOf("a", "hi"), 3)
        assertEquals(emptyList<String>(), long)
        assertEquals(listOf("a", "hi"), short)
    }

    @Test fun `empty input produces two empty lists`() {
        val (long, short) = splitByLength(emptyList(), 3)
        assertEquals(emptyList<String>(), long)
        assertEquals(emptyList<String>(), short)
    }
}
