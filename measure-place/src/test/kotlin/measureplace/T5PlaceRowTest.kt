package measureplace

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 5: placement is a separate pass from measurement.
class T5PlaceRowTest {
    @Test fun `each child's x is the cumulative width of its earlier siblings`() {
        val children = listOf(Size(10, 5), Size(20, 5), Size(30, 5))
        val result = placeRow(children)
        assertEquals(listOf(Position(0, 0), Position(10, 0), Position(30, 0)), result)
    }

    @Test fun `a single child sits at the origin`() {
        assertEquals(listOf(Position(0, 0)), placeRow(listOf(Size(50, 50))))
    }

    @Test fun `no children means no positions`() {
        assertEquals(emptyList<Position>(), placeRow(emptyList()))
    }
}
