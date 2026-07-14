package measureplace

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 3: sizes resolve back up, padding adds itself back around the child.
class T3SizeAfterPaddingTest {
    @Test fun `adds padding back around the measured child`() {
        val incoming = Constraints(minWidth = 0, maxWidth = 1000, minHeight = 0, maxHeight = 1000)
        val result = sizeAfterPadding(Size(50, 50), horizontal = 20, vertical = 10, incoming = incoming)
        assertEquals(Size(70, 60), result)
    }

    @Test fun `the padded total is coerced into the parent's own constraints`() {
        val incoming = Constraints(minWidth = 0, maxWidth = 60, minHeight = 0, maxHeight = 1000)
        val result = sizeAfterPadding(Size(50, 50), horizontal = 20, vertical = 10, incoming = incoming)
        assertEquals(60, result.width)
        assertEquals(60, result.height)
    }

    @Test fun `zero padding just coerces the child size unchanged`() {
        val incoming = Constraints(minWidth = 0, maxWidth = 1000, minHeight = 0, maxHeight = 1000)
        assertEquals(Size(50, 50), sizeAfterPadding(Size(50, 50), horizontal = 0, vertical = 0, incoming = incoming))
    }
}
