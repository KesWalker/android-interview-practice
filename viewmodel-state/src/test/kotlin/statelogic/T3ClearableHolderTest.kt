package statelogic

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 3: tearing down everything scoped to a holder in one shot, the onCleared idiom.
class T3ClearableHolderTest {
    @Test fun `keeps running while active`() = runTest {
        val holder = ClearableHolder(StandardTestDispatcher(testScheduler))
        var completed = false
        holder.scope.launch {
            delay(50)
            completed = true
        }

        advanceUntilIdle()

        assertTrue(completed)
    }

    @Test fun `cancels in-flight work once cleared`() = runTest {
        val holder = ClearableHolder(StandardTestDispatcher(testScheduler))
        var ticks = 0
        holder.scope.launch {
            repeat(10) {
                delay(100)
                ticks++
            }
        }

        advanceTimeBy(250)
        runCurrent()
        holder.clear()
        advanceUntilIdle()

        assertEquals(2, ticks)
    }

    @Test fun `does not run work launched after clear`() = runTest {
        val holder = ClearableHolder(StandardTestDispatcher(testScheduler))
        holder.clear()

        var ran = false
        holder.scope.launch { ran = true }
        advanceUntilIdle()

        assertFalse(ran)
    }
}
