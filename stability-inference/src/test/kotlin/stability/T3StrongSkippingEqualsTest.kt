package stability

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 3: Strong Skipping compares stable params by == and unstable params
// by ===.
class T3StrongSkippingEqualsTest {
    private val stringType = TypeDescriptor.Primitive("String")
    private val listType = TypeDescriptor.InterfaceType("List")

    @Test fun `stable type compares equal by content, even across different instances`() {
        val old = "hello".let { StringBuilder(it).toString() }
        val new = "hello".let { StringBuilder(it).toString() }
        assertTrue(old !== new)
        assertTrue(strongSkippingEquals(stringType, old, new))
    }

    @Test fun `unstable type with equal content but different references does not compare equal`() {
        val old = listOf("a", "b")
        val new = listOf("a", "b")
        assertTrue(old !== new)
        assertTrue(old == new)
        assertFalse(strongSkippingEquals(listType, old, new))
    }

    @Test fun `unstable type compares equal when it's the same reference`() {
        val list = listOf("a", "b")
        assertTrue(strongSkippingEquals(listType, list, list))
    }
}
