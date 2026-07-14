package measureplace

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 1: clamp a requested size into a constraints box.
class T1CoerceToConstraintsTest {
    @Test fun `size already within bounds is unchanged`() {
        val constraints = Constraints(minWidth = 0, maxWidth = 100, minHeight = 0, maxHeight = 50)
        assertEquals(Size(40, 20), coerceToConstraints(constraints, Size(40, 20)))
    }

    @Test fun `size below the minimum is clamped up`() {
        val constraints = Constraints(minWidth = 20, maxWidth = 100, minHeight = 10, maxHeight = 50)
        assertEquals(Size(20, 10), coerceToConstraints(constraints, Size(5, 2)))
    }

    @Test fun `size above the maximum is clamped down`() {
        val constraints = Constraints(minWidth = 0, maxWidth = 100, minHeight = 0, maxHeight = 50)
        assertEquals(Size(100, 50), coerceToConstraints(constraints, Size(200, 999)))
    }
}
