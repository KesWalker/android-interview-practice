package delegation

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 2: custom property delegate via getValue/setValue.
class T2MeterTest {
    @Test fun `starts at the initial value`() {
        assertEquals(0, Meter().distance)
    }

    @Test fun `stores a normal write`() {
        val meter = Meter()
        meter.distance = 5
        assertEquals(5, meter.distance)
    }

    @Test fun `clamps a negative write to zero`() {
        val meter = Meter()
        meter.distance = -3
        assertEquals(0, meter.distance)
    }
}
