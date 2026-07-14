package easing

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 6: interrupting a running spring must continue from its current
// value and velocity, never snap either one to zero.
class T6RetargetSpringTest {
    @Test fun `continues from the interrupted value and velocity rather than snapping`() {
        val result = runInterruptedSpring(
            start = 0f, target1 = 10f, steps1 = 5,
            target2 = 0f, steps2 = 10,
            stiffness = 2f, damping = 1f, dt = 0.1f
        )
        // If velocity (or value) were wrongly reset to 0/start at the
        // handoff, these numbers come out very different, see for yourself
        // by temporarily zeroing the carried-over state.
        assertEquals(3.8168f, result.value, 0.01f)
        assertEquals(-2.6571f, result.velocity, 0.01f)
    }

    @Test fun `zero steps after retargeting leaves the interrupted state untouched`() {
        val result = runInterruptedSpring(
            start = 0f, target1 = 10f, steps1 = 5,
            target2 = 0f, steps2 = 0,
            stiffness = 2f, damping = 1f, dt = 0.1f
        )
        assertEquals(2.5068f, result.value, 0.005f)
        assertEquals(7.5182f, result.velocity, 0.005f)
    }
}
