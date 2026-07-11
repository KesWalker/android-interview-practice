package properties

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 6: a computed getter with no backing field.
class RectangleAreaTest {
    @Test fun `computes width times height`() {
        val rect = Rectangle(3, 4)
        assertEquals(12, rect.area)
    }

    @Test fun `updates immediately after width changes`() {
        val rect = Rectangle(3, 4)
        rect.width = 10
        assertEquals(40, rect.area)
    }

    @Test fun `updates immediately after height changes`() {
        val rect = Rectangle(3, 4)
        rect.height = 1
        assertEquals(3, rect.area)
    }
}
