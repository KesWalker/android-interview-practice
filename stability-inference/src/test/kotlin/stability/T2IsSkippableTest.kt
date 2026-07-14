package stability

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 2: a composable is skippable only if every param type is stable.
class T2IsSkippableTest {
    @Test fun `no params is trivially skippable`() {
        assertTrue(isSkippable(emptyList()))
    }

    @Test fun `all-stable params are skippable`() {
        val params = listOf(
            TypeDescriptor.Primitive("String"),
            TypeDescriptor.ClassType(
                "Point",
                listOf(
                    FieldDescriptor("x", TypeDescriptor.Primitive("Int")),
                    FieldDescriptor("y", TypeDescriptor.Primitive("Int"))
                )
            )
        )
        assertTrue(isSkippable(params))
    }

    @Test fun `a single unstable param makes the whole composable unskippable`() {
        val params = listOf(
            TypeDescriptor.Primitive("String"),
            TypeDescriptor.InterfaceType("List")
        )
        assertFalse(isSkippable(params))
    }
}
