package testflow

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 5: UnconfinedTestDispatcher's eager dispatch vs StandardTestDispatcher's queued dispatch.
class EagerAuditLogTest {
    @Test fun `under UnconfinedTestDispatcher, start collects the first emission with no advanceUntilIdle needed`() = runTest {
        val events = MutableSharedFlow<String>(replay = 1)
        events.tryEmit("boot")
        val logger = AuditLogger(UnconfinedTestDispatcher(testScheduler), events)

        logger.start()

        assertEquals(listOf("boot"), logger.entries.value)
    }

    @Test fun `the same class under StandardTestDispatcher needs advanceUntilIdle before anything shows up`() = runTest {
        val events = MutableSharedFlow<String>(replay = 1)
        events.tryEmit("boot")
        val logger = AuditLogger(StandardTestDispatcher(testScheduler), events)

        logger.start()

        assertEquals(emptyList<String>(), logger.entries.value)

        advanceUntilIdle()

        assertEquals(listOf("boot"), logger.entries.value)
    }

    @Test fun `keeps appending every emission after start, not just the first`() = runTest {
        val events = MutableSharedFlow<String>(replay = 0, extraBufferCapacity = 8)
        val logger = AuditLogger(UnconfinedTestDispatcher(testScheduler), events)

        logger.start()
        events.tryEmit("one")
        events.tryEmit("two")
        runCurrent()

        assertEquals(listOf("one", "two"), logger.entries.value)
    }
}
