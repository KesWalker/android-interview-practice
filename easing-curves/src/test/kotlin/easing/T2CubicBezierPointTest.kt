package easing

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 2: a point on a cubic Bezier curve, evaluated directly at t.
class T2CubicBezierPointTest {
    private val p0 = Point(0f, 0f)
    private val p1 = Point(0f, 1f)
    private val p2 = Point(1f, 1f)
    private val p3 = Point(1f, 0f)

    @Test fun `t 0 returns the first control point`() {
        assertEquals(Point(0f, 0f), cubicBezierPoint(p0, p1, p2, p3, 0f))
    }

    @Test fun `t 1 returns the last control point`() {
        assertEquals(Point(1f, 0f), cubicBezierPoint(p0, p1, p2, p3, 1f))
    }

    @Test fun `t 0_5 blends all four control points`() {
        val result = cubicBezierPoint(p0, p1, p2, p3, 0.5f)
        assertEquals(0.5f, result.x, 0.001f)
        assertEquals(0.75f, result.y, 0.001f)
    }
}
