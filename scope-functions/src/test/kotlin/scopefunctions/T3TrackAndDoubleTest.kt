package scopefunctions

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 3: also for side effects mid-chain.
class T3TrackAndDoubleTest {
    @Test fun `returns double the number`() = assertEquals(10, trackAndDouble(5, mutableListOf()))

    @Test fun `logs the original, not the doubled, value`() {
        val log = mutableListOf<Int>()
        trackAndDouble(5, log)
        assertEquals(listOf(5), log)
    }

    @Test fun `appends to existing log entries`() {
        val log = mutableListOf(1, 2)
        trackAndDouble(3, log)
        assertEquals(listOf(1, 2, 3), log)
    }
}
