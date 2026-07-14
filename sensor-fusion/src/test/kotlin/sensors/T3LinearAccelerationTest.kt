package sensors

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 3: strip gravity out of a raw reading.
class T3LinearAccelerationTest {
    @Test fun `subtracts gravity per axis`() {
        val raw = Vector3(2.5f, 0f, 10.5f)
        val gravity = Vector3(0.5f, 0f, 10.5f)

        val result = linearAcceleration(raw, gravity)

        assertEquals(2f, result.x, 0.0001f)
        assertEquals(0f, result.y, 0.0001f)
        assertEquals(0f, result.z, 0.0001f)
    }

    @Test fun `raw equal to gravity means no motion`() {
        val v = Vector3(1f, 2f, 9.8f)

        val result = linearAcceleration(v, v)

        assertEquals(0f, result.x, 0.0001f)
        assertEquals(0f, result.y, 0.0001f)
        assertEquals(0f, result.z, 0.0001f)
    }

    @Test fun `negative axis motion is preserved`() {
        val raw = Vector3(-3f, 4f, 9.8f)
        val gravity = Vector3(0f, 0f, 9.8f)

        val result = linearAcceleration(raw, gravity)

        assertEquals(-3f, result.x, 0.0001f)
        assertEquals(4f, result.y, 0.0001f)
        assertEquals(0f, result.z, 0.0001f)
    }
}
