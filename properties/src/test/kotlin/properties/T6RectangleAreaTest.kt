package properties

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 6: a computed getter with no backing field. You declare `area` from
// scratch in Tasks.kt; found by name once it exists.
class T6RectangleAreaTest {
    private fun areaOf(rect: Rectangle): Any? {
        val getter = rect.property("getArea")
            ?: notDeclaredYet(
                "t6: Rectangle.area",
                "Declare a val `area: Int` in Rectangle computed fresh from the " +
                    "current width and height on every read - a getter with no " +
                    "backing field, so it can never go stale.",
            )
        return getter.call(rect)
    }

    @Test fun `computes width times height`() {
        val rect = Rectangle(3, 4)
        assertEquals(12, areaOf(rect))
    }

    @Test fun `updates immediately after width changes`() {
        val rect = Rectangle(3, 4)
        rect.width = 10
        assertEquals(40, areaOf(rect))
    }

    @Test fun `updates immediately after height changes`() {
        val rect = Rectangle(3, 4)
        rect.height = 1
        assertEquals(3, areaOf(rect))
    }
}
