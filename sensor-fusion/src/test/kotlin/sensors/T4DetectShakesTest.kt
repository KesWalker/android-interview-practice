package sensors

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 4: threshold plus debounce, so one shake isn't counted many times.
class T4DetectShakesTest {
    @Test fun `a burst of over-threshold samples within the debounce window counts once`() {
        val samples = listOf(
            SensorSample(0, 5f),
            SensorSample(20, 6f),
            SensorSample(40, 4f)
        )

        assertEquals(listOf(0L), detectShakes(samples, threshold = 3f, debounceMs = 100))
    }

    @Test fun `two bursts separated by more than the debounce window count as two shakes`() {
        val samples = listOf(
            SensorSample(0, 5f),
            SensorSample(200, 5f)
        )

        assertEquals(listOf(0L, 200L), detectShakes(samples, threshold = 3f, debounceMs = 100))
    }

    @Test fun `debounce is measured from the last COUNTED shake, not the last over-threshold sample`() {
        val samples = listOf(
            SensorSample(0, 5f),
            SensorSample(50, 5f),
            SensorSample(150, 5f)
        )

        // 50 is suppressed (only 50ms since the shake counted at 0), but 150 is
        // 150ms after the shake counted at 0, so it counts as a new one, even
        // though it's only 100ms after the suppressed sample at 50.
        assertEquals(listOf(0L, 150L), detectShakes(samples, threshold = 3f, debounceMs = 100))
    }

    @Test fun `samples that never cross the threshold produce no shakes`() {
        val samples = listOf(SensorSample(0, 1f), SensorSample(50, 2f))

        assertEquals(emptyList<Long>(), detectShakes(samples, threshold = 3f, debounceMs = 100))
    }

    @Test fun `empty samples produce no shakes`() {
        assertEquals(emptyList<Long>(), detectShakes(emptyList(), threshold = 3f, debounceMs = 100))
    }
}
