package extensions

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

// Task 3: operator function declared as an extension. Indexing syntax like
// point[0] desugars to a function named `get` - this test checks the behavior;
// prove the bracket syntax to your pair by actually calling point[0] yourself.
class T3PointIndexingTest {
    private fun pointGet(p: Point, index: Int): Any? {
        val m = findExtension("get", Point::class.java, Int::class.javaPrimitiveType!!)
            ?: notDeclaredYet(
                "t3: Point indexing",
                "Declare a top-level OPERATOR extension function on Point that makes " +
                    "point[0] work. Indexing desugars to a specially-named function " +
                    "with the `operator` modifier - which name does `[]` map to?",
            )
        return m.call(p, index)
    }

    @Test fun `index 0 is x`() = assertEquals(3, pointGet(Point(3, 4), 0))
    @Test fun `index 1 is y`() = assertEquals(4, pointGet(Point(3, 4), 1))

    @Test fun `other index throws`() {
        assertThrows(IndexOutOfBoundsException::class.java) { pointGet(Point(1, 2), 2) }
    }
}
