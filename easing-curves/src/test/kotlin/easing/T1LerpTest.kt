package easing

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 1: linear interpolation, the building block for everything else here.
class T1LerpTest {
    @Test fun `fraction 0 and 1 return the endpoints`() {
        assertEquals(0f, lerp(0f, 10f, 0f), 0.0001f)
        assertEquals(10f, lerp(0f, 10f, 1f), 0.0001f)
    }

    @Test fun `fraction 0_5 returns the midpoint`() {
        assertEquals(5f, lerp(0f, 10f, 0.5f), 0.0001f)
        assertEquals(7.5f, lerp(10f, 0f, 0.25f), 0.0001f)
    }

    @Test fun `fractions outside 0 to 1 extrapolate past the ends`() {
        assertEquals(20f, lerp(0f, 10f, 2f), 0.0001f)
        assertEquals(-10f, lerp(0f, 10f, -1f), 0.0001f)
    }
}
