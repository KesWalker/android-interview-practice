package startup

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 1: classify a launch from what already exists.
class T1ClassifyStartupTest {
    @Test fun `no process is cold`() {
        assertEquals(
            StartupState.COLD,
            classifyStartup(processAlive = false, activityExists = false)
        )
    }

    @Test fun `no process is cold even if activityExists is stale`() {
        assertEquals(
            StartupState.COLD,
            classifyStartup(processAlive = false, activityExists = true)
        )
    }

    @Test fun `process alive without the activity is warm`() {
        assertEquals(
            StartupState.WARM,
            classifyStartup(processAlive = true, activityExists = false)
        )
    }

    @Test fun `process alive with the activity is hot`() {
        assertEquals(
            StartupState.HOT,
            classifyStartup(processAlive = true, activityExists = true)
        )
    }
}
