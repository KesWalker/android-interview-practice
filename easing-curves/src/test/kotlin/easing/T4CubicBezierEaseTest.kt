package easing

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 4: a real cubic-bezier easing curve, solved by binary search.
class T4CubicBezierEaseTest {
    // Material's standard "ease" curve: cubic-bezier(0.25, 0.1, 0.25, 1.0).
    private val x1 = 0.25f
    private val y1 = 0.1f
    private val x2 = 0.25f
    private val y2 = 1.0f

    @Test fun `the curve's own endpoints are fixed`() {
        assertEquals(0f, cubicBezierEase(x1, y1, x2, y2, 0f), 0.01f)
        assertEquals(1f, cubicBezierEase(x1, y1, x2, y2, 1f), 0.01f)
    }

    @Test fun `matches the known shape of the standard ease curve`() {
        assertEquals(0.409f, cubicBezierEase(x1, y1, x2, y2, 0.25f), 0.01f)
        assertEquals(0.802f, cubicBezierEase(x1, y1, x2, y2, 0.5f), 0.01f)
        assertEquals(0.960f, cubicBezierEase(x1, y1, x2, y2, 0.75f), 0.01f)
    }

    @Test fun `a linear curve is the identity`() {
        assertEquals(0.3f, cubicBezierEase(0f, 0f, 1f, 1f, 0.3f), 0.01f)
        assertEquals(0.7f, cubicBezierEase(0f, 0f, 1f, 1f, 0.7f), 0.01f)
    }
}
