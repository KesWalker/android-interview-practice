package framebudget

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 3: jank rate and nearest-rank percentiles over a run of frames.
class T3AnalyzeFramesTest {
    private val budgetMs = 1000.0 / 60

    @Test fun `mixed frames produce a jank rate and percentiles by nearest rank`() {
        val durations = listOf(10.0, 12.0, 14.0, 16.0, 18.0, 20.0, 100.0, 12.0, 15.0, 13.0)

        val stats = analyzeFrames(durations, budgetMs)

        assertEquals(30.0, stats.jankRatePercent, 0.0001)
        assertEquals(14.0, stats.p50, 0.0001)
        assertEquals(20.0, stats.p90, 0.0001)
        assertEquals(100.0, stats.p99, 0.0001)
    }

    @Test fun `all-smooth frames have a zero jank rate`() {
        val durations = listOf(5.0, 6.0, 7.0, 8.0)

        val stats = analyzeFrames(durations, budgetMs)

        assertEquals(0.0, stats.jankRatePercent, 0.0001)
        assertEquals(6.0, stats.p50, 0.0001)
        assertEquals(8.0, stats.p90, 0.0001)
        assertEquals(8.0, stats.p99, 0.0001)
    }
}
