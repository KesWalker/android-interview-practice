package classes

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 2: producing an updated copy of an immutable value.
class T2MovedPointTest {
    @Test fun `shifts both coordinates`() {
        assertEquals(Point(4, 6), moved(Point(1, 2), 3, 4))
    }

    @Test fun `original point is left untouched`() {
        val original = Point(1, 2)
        moved(original, 3, 4)
        assertEquals(Point(1, 2), original)
    }

    @Test fun `negative deltas move the point back`() {
        assertEquals(Point(0, 0), moved(Point(5, 5), -5, -5))
    }
}
