package stability

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 4: a lambda is stable iff everything it captures is stable.
class T4LambdaIsStableTest {
    @Test fun `a lambda that captures nothing is stable`() {
        assertTrue(lambdaIsStable(emptyList()))
    }

    @Test fun `a lambda capturing only stable types is stable`() {
        val captures = listOf(TypeDescriptor.Primitive("Int"), TypeDescriptor.Primitive("String"))
        assertTrue(lambdaIsStable(captures))
    }

    @Test fun `a lambda capturing an unstable type is unstable`() {
        val captures = listOf(TypeDescriptor.Primitive("Int"), TypeDescriptor.InterfaceType("List"))
        assertFalse(lambdaIsStable(captures))
    }
}
