package extensions

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

// Task 3: operator function declared as an extension.
class PointIndexingTest {
    @Test fun `index 0 is x`() = assertEquals(3, Point(3, 4)[0])
    @Test fun `index 1 is y`() = assertEquals(4, Point(3, 4)[1])

    @Test fun `other index throws`() {
        assertThrows(IndexOutOfBoundsException::class.java) { Point(1, 2)[2] }
    }
}
