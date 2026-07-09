package serialization

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DecodeShapesTest {

    @Test
    fun `decodes each element to its matching subtype`() {
        val json = """[{"type":"circle","radius":2.0},{"type":"square","side":3.0}]"""
        assertEquals(listOf(Circle(2.0), Square(3.0)), decodeShapes(json))
    }

    @Test
    fun `decodes a single-element list`() {
        assertEquals(listOf(Square(5.0)), decodeShapes("""[{"type":"square","side":5.0}]"""))
    }
}
