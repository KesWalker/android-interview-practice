package classes

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 4: wrapping and unwrapping a value class.
class T4DoubleDistanceTest {
    @Test fun `doubles a positive distance`() {
        assertEquals(Meters(6.0), doubled(Meters(3.0)))
    }

    @Test fun `doubling zero is zero`() {
        assertEquals(Meters(0.0), doubled(Meters(0.0)))
    }

    @Test fun `works with fractional distances`() {
        assertEquals(Meters(5.0), doubled(Meters(2.5)))
    }
}
