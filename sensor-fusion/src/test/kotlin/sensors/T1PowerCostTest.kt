package sensors

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 1: sampling rate to power cost.
class T1PowerCostTest {
    @Test fun `NORMAL is the cheapest`() {
        assertEquals(PowerCost.LOW, powerCost(SamplingRate.NORMAL))
    }

    @Test fun `UI is medium`() {
        assertEquals(PowerCost.MEDIUM, powerCost(SamplingRate.UI))
    }

    @Test fun `GAME is high`() {
        assertEquals(PowerCost.HIGH, powerCost(SamplingRate.GAME))
    }

    @Test fun `FASTEST is the most expensive`() {
        assertEquals(PowerCost.MAXIMUM, powerCost(SamplingRate.FASTEST))
    }
}
