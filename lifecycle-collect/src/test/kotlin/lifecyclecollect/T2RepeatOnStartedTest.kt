package lifecyclecollect

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 2: repeatOnLifecycle(STARTED) -- cancels, doesn't just pause.
class T2RepeatOnStartedTest {
    @Test fun `does not collect anything while the owner is below STARTED`() = runTest {
        val owner = FakeLifecycleOwner(LifecycleState.CREATED)
        val channel = Channel<Int>(Channel.UNLIMITED)
        val counter = Counter()
        val consumed = mutableListOf<Int>()

        repeatOnStarted(owner, backgroundScope, countedSource(channel, counter)) { consumed.add(it) }
        runCurrent()

        channel.trySend(1)
        channel.trySend(2)
        runCurrent()

        assertEquals(0, counter.value)
        assertEquals(emptyList<Int>(), consumed)

        owner.moveTo(LifecycleState.STARTED)
        runCurrent()

        assertEquals(2, counter.value)
        assertEquals(listOf(1, 2), consumed)
    }

    @Test fun `cancels the collector the instant it drops below STARTED`() = runTest {
        val owner = FakeLifecycleOwner(LifecycleState.STARTED)
        val channel = Channel<Int>(Channel.UNLIMITED)
        val counter = Counter()
        val consumed = mutableListOf<Int>()

        repeatOnStarted(owner, backgroundScope, countedSource(channel, counter)) { consumed.add(it) }
        runCurrent()

        channel.trySend(1)
        runCurrent()
        assertEquals(1, counter.value)
        assertEquals(listOf(1), consumed)

        owner.moveTo(LifecycleState.CREATED)
        runCurrent()

        channel.trySend(2)
        runCurrent()

        assertEquals(1, counter.value)
        assertEquals(listOf(1), consumed)
    }

    @Test fun `restarts on the next STARTED and picks up whatever was waiting`() = runTest {
        val owner = FakeLifecycleOwner(LifecycleState.STARTED)
        val channel = Channel<Int>(Channel.UNLIMITED)
        val counter = Counter()
        val consumed = mutableListOf<Int>()

        repeatOnStarted(owner, backgroundScope, countedSource(channel, counter)) { consumed.add(it) }
        runCurrent()

        channel.trySend(1)
        runCurrent()

        owner.moveTo(LifecycleState.CREATED)
        runCurrent()
        channel.trySend(2)
        runCurrent()
        assertEquals(1, counter.value)

        owner.moveTo(LifecycleState.STARTED)
        runCurrent()

        assertEquals(2, counter.value)
        assertEquals(listOf(1, 2), consumed)
    }
}
