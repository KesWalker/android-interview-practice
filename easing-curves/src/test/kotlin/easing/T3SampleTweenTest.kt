package easing

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 3: a keyframe tween, sampled at an arbitrary time and clamped at the ends.
class T3SampleTweenTest {
    private val keyframes = listOf(
        Keyframe(0f, 0f),
        Keyframe(0.5f, 100f),
        Keyframe(1f, 40f)
    )

    @Test fun `samples between the first pair of keyframes`() {
        assertEquals(50f, sampleTween(keyframes, 0.25f), 0.001f)
    }

    @Test fun `samples between the second pair of keyframes`() {
        assertEquals(70f, sampleTween(keyframes, 0.75f), 0.001f)
    }

    @Test fun `exact keyframe times return that keyframe's value`() {
        assertEquals(0f, sampleTween(keyframes, 0f), 0.001f)
        assertEquals(100f, sampleTween(keyframes, 0.5f), 0.001f)
        assertEquals(40f, sampleTween(keyframes, 1f), 0.001f)
    }

    @Test fun `times before the start or after the end clamp to the nearest keyframe`() {
        assertEquals(0f, sampleTween(keyframes, -0.5f), 0.001f)
        assertEquals(40f, sampleTween(keyframes, 1.5f), 0.001f)
    }
}
