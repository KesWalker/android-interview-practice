package extensions

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 5: extensions resolve by the declared type of the reference, not the
// runtime type — static dispatch, unlike a real member function.
class T5KindsOfTest {
    @Test fun `dispatches on the declared type, not the runtime type`() {
        val shapes: List<Shape> = listOf(Circle(), Circle())
        assertEquals(listOf("shape", "shape"), kindsOf(shapes))
    }
}
