package measureplace

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 4: a Row's own size is the sum of widths and the tallest child.
class T4MeasureRowTest {
    @Test fun `sums widths and takes the tallest child`() {
        val incoming = Constraints(minWidth = 0, maxWidth = 1000, minHeight = 0, maxHeight = 1000)
        val children = listOf(Size(10, 20), Size(30, 5), Size(15, 40))
        assertEquals(Size(55, 40), measureRow(children, incoming))
    }

    @Test fun `an empty row coerces to the minimum`() {
        val incoming = Constraints(minWidth = 5, maxWidth = 1000, minHeight = 2, maxHeight = 1000)
        assertEquals(Size(5, 2), measureRow(emptyList(), incoming))
    }

    @Test fun `tight max clamps the row's own size even though children overflow it`() {
        val incoming = Constraints(minWidth = 0, maxWidth = 30, minHeight = 0, maxHeight = 1000)
        val children = listOf(Size(20, 10), Size(20, 10))
        assertEquals(30, measureRow(children, incoming).width)
    }
}
