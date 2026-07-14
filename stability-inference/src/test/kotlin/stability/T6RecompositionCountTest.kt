package stability

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 6: count how many calls in a sequence actually recompose.
class T6RecompositionCountTest {
    private val stringType = TypeDescriptor.Primitive("String")
    private val listType = TypeDescriptor.InterfaceType("List")

    @Test fun `stable params only recompose when the value actually changes`() {
        val params = listOf(stringType)
        val sequence = listOf(
            listOf("a"),
            listOf("a"),
            listOf("b"),
            listOf("b"),
            listOf("c")
        )
        // initial + "a"->"b" + "b"->"c" = 3
        assertEquals(3, countRecompositions(params, sequence))
    }

    @Test fun `unstable params recompose on every call regardless of content`() {
        val params = listOf(listType)
        val sequence = listOf(
            listOf(listOf("x")),
            listOf(listOf("x")),
            listOf(listOf("x"))
        )
        assertEquals(3, countRecompositions(params, sequence))
    }

    @Test fun `a single-entry sequence counts only the initial composition`() {
        val params = listOf(stringType)
        assertEquals(1, countRecompositions(params, listOf(listOf("only"))))
    }
}
