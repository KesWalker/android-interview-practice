package classes

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 7: enum for a closed set of same-shape cases.
class T7NextLightTest {
    @Test fun `red advances to green`() {
        assertEquals(TrafficLight.GREEN, nextLight(TrafficLight.RED))
    }

    @Test fun `green advances to yellow`() {
        assertEquals(TrafficLight.YELLOW, nextLight(TrafficLight.GREEN))
    }

    @Test fun `yellow advances back to red`() {
        assertEquals(TrafficLight.RED, nextLight(TrafficLight.YELLOW))
    }
}
