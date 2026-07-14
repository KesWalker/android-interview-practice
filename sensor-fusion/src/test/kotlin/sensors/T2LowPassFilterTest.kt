package sensors

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 2: isolate gravity from a raw reading.
class T2LowPassFilterTest {
    @Test fun `blends gravity toward raw by (1 - alpha) per axis`() {
        val gravity = Vector3(0f, 0f, 10f)
        val raw = Vector3(2f, 0f, 10f)

        val result = lowPassFilter(gravity, raw, alpha = 0.8f)

        assertEquals(0.4f, result.x, 0.0001f)
        assertEquals(0f, result.y, 0.0001f)
        assertEquals(10f, result.z, 0.0001f)
    }

    @Test fun `alpha of 1 leaves gravity completely unchanged`() {
        val gravity = Vector3(0f, 0f, 9.8f)
        val raw = Vector3(5f, -3f, 2f)

        val result = lowPassFilter(gravity, raw, alpha = 1f)

        assertEquals(gravity, result)
    }

    @Test fun `alpha of 0 snaps straight to raw`() {
        val gravity = Vector3(0f, 0f, 9.8f)
        val raw = Vector3(5f, -3f, 2f)

        val result = lowPassFilter(gravity, raw, alpha = 0f)

        assertEquals(raw, result)
    }
}
