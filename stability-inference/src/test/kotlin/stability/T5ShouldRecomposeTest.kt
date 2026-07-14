package stability

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 5: combine skippability with Strong Skipping's per-param comparison.
class T5ShouldRecomposeTest {
    private val stringType = TypeDescriptor.Primitive("String")
    private val listType = TypeDescriptor.InterfaceType("List")

    @Test fun `unskippable composable always recomposes, even with identical args`() {
        val params = listOf(listType)
        val args = listOf(listOf("a"))
        assertTrue(shouldRecompose(params, args, args))
    }

    @Test fun `skippable composable does not recompose when every stable param is equal`() {
        val params = listOf(stringType, stringType)
        val old = listOf("hello", "world")
        val new = listOf("hello", "world")
        assertFalse(shouldRecompose(params, old, new))
    }

    @Test fun `skippable composable recomposes when one stable param changes`() {
        val params = listOf(stringType, stringType)
        val old = listOf("hello", "world")
        val new = listOf("hello", "there")
        assertTrue(shouldRecompose(params, old, new))
    }
}
