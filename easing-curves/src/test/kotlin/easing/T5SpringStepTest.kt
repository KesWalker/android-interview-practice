package easing

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 5: one semi-implicit Euler step of a damped spring.
class T5SpringStepTest {
    private fun simulate(damping: Float, steps: Int = 300, dt: Float = 1f / 60f, target: Float = 10f): Pair<Float, Float> {
        var state = AnimationState(value = 0f, velocity = 0f)
        var maxValue = state.value
        repeat(steps) {
            state = springStep(state, target, stiffness = 20f, damping = damping, dt = dt)
            if (state.value > maxValue) maxValue = state.value
        }
        return state.value to maxValue
    }

    @Test fun `a single step moves value and velocity toward the target`() {
        val start = AnimationState(value = 0f, velocity = 0f)
        val result = springStep(start, target = 10f, stiffness = 20f, damping = 5f, dt = 1f / 60f)
        assertTrue(result.velocity > 0f, "velocity should move toward the target, was ${result.velocity}")
        assertTrue(result.value > 0f, "value should move toward the target, was ${result.value}")
    }

    @Test fun `a heavily damped spring never overshoots the target`() {
        val (finalValue, maxValue) = simulate(damping = 20f)
        assertTrue(maxValue <= 10f + 0.05f, "expected no overshoot, max value seen was $maxValue")
        assertTrue(finalValue in 9.0f..10.05f, "expected to settle near the target, was $finalValue")
    }

    @Test fun `a lightly damped spring overshoots the target before settling`() {
        val (_, maxValue) = simulate(damping = 1f)
        assertTrue(maxValue > 10f + 1f, "expected a bouncy overshoot past the target, max value seen was $maxValue")
    }
}
