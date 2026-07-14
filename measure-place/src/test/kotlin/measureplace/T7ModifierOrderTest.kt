package measureplace

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 7: modifier order changes the result, the classic Compose gotcha.
class T7ModifierOrderTest {
    private val generous = Constraints(minWidth = 0, maxWidth = 1000, minHeight = 0, maxHeight = 1000)

    @Test fun `padding then size wraps the fixed size in padding`() {
        val modifiers = listOf(LayoutModifier.PaddingAll(16), LayoutModifier.ExactSize(100))
        assertEquals(Size(132, 132), sizeAfterModifierChain(modifiers, generous))
    }

    @Test fun `size then padding fixes the outer size regardless of what's inside`() {
        val modifiers = listOf(LayoutModifier.ExactSize(100), LayoutModifier.PaddingAll(16))
        assertEquals(Size(100, 100), sizeAfterModifierChain(modifiers, generous))
    }

    @Test fun `no modifiers fills all the space the incoming constraints allow`() {
        val incoming = Constraints(minWidth = 0, maxWidth = 200, minHeight = 0, maxHeight = 80)
        assertEquals(Size(200, 80), sizeAfterModifierChain(emptyList(), incoming))
    }
}
