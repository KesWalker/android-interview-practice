package startup

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 2: pull time-to-initial-display out of an unordered trace.
class T2TimeToInitialDisplayTest {
    @Test fun `computes the gap between process start and first frame`() {
        val events = listOf(
            TraceEvent("bindApplication", 120),
            TraceEvent("processStart", 0),
            TraceEvent("activityCreate", 210),
            TraceEvent("firstFrame", 480)
        )

        assertEquals(480L, timeToInitialDisplay(events))
    }

    @Test fun `ignores unrelated events and event list order`() {
        val events = listOf(
            TraceEvent("firstFrame", 900),
            TraceEvent("someOtherMarker", 999),
            TraceEvent("processStart", 300)
        )

        assertEquals(600L, timeToInitialDisplay(events))
    }
}
