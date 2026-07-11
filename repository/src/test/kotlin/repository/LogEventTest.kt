package repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 6: operation scope - app-oriented work outliving a screen-scoped caller.
class LogEventTest {
    @Test fun `upload runs on appScope and survives the caller scope being cancelled`() = runTest {
        val appScope = CoroutineScope(StandardTestDispatcher(testScheduler) + SupervisorJob())
        val callerScope = CoroutineScope(StandardTestDispatcher(testScheduler) + SupervisorJob())
        var uploaded: String? = null

        callerScope.launch {
            logEvent(appScope, "screen_view") { event ->
                delay(100)
                uploaded = event
            }
        }
        runCurrent()
        callerScope.cancel()

        advanceUntilIdle()

        assertEquals("screen_view", uploaded)
    }
}
