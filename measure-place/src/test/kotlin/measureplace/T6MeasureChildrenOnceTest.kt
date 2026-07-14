package measureplace

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 6: measuring a child twice in one pass is illegal, detect it.
class T6MeasureChildrenOnceTest {
    @Test fun `returns each child's size in call order`() {
        val sizes = mapOf(1 to Size(10, 10), 2 to Size(20, 20), 3 to Size(30, 30))
        val result = measureChildrenOnce(listOf(1, 2, 3), sizes)
        assertEquals(listOf(Size(10, 10), Size(20, 20), Size(30, 30)), result)
    }

    @Test fun `measuring the same child id twice throws`() {
        val sizes = mapOf(1 to Size(10, 10), 2 to Size(20, 20))
        val exception = assertThrows(MultipleMeasureException::class.java) {
            measureChildrenOnce(listOf(1, 2, 1), sizes)
        }
        assertTrue(exception.message!!.contains("1"))
    }

    @Test fun `no measure calls means no sizes`() {
        assertEquals(emptyList<Size>(), measureChildrenOnce(emptyList(), emptyMap()))
    }
}
