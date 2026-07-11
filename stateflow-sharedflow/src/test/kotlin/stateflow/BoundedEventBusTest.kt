package stateflow

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 5: MutableSharedFlow's default SUSPEND onBufferOverflow policy.
//
// Pumped with runCurrent()/advanceTimeBy() rather than advanceUntilIdle(): the
// collector lives in backgroundScope, which never completes.
class BoundedEventBusTest {
    // The footgun first: with NO subscriber, emit() does not suspend and does not
    // queue. A default MutableSharedFlow has replay = 0 and no buffer, so the
    // value goes nowhere and a collector arriving later never sees it.
    @Test fun `with no collector, publish does not suspend and the value is dropped`() = runTest {
        val bus = BoundedEventBus()
        var published = false

        launch { bus.publish(1); published = true }
        runCurrent()
        assertTrue(published)

        val received = mutableListOf<Int>()
        backgroundScope.launch { bus.events.collect { received.add(it) } }
        runCurrent()

        assertEquals(emptyList<Int>(), received)
    }

    // The SUSPEND policy proper: once a subscriber exists but isn't ready for the
    // next value, emit() suspends the publisher until it takes it. Nothing is lost
    // and nothing is dropped -- the producer is simply backpressured.
    @Test fun `publish suspends until a slow collector takes the value`() = runTest {
        val bus = BoundedEventBus()
        val received = mutableListOf<Int>()

        backgroundScope.launch {
            bus.events.collect { value ->
                delay(100)
                received.add(value)
            }
        }
        runCurrent()

        var allPublished = false
        launch {
            bus.publish(1)
            bus.publish(2)
            allPublished = true
        }
        runCurrent()

        // The collector is busy in delay(), so the second emit can't hand its
        // value over yet -- the publisher is still suspended inside publish(2).
        assertFalse(allPublished)

        advanceTimeBy(250)
        runCurrent()

        assertTrue(allPublished)
        assertEquals(listOf(1, 2), received)
    }
}
