package lifecyclecollect

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 3: launchWhenStarted -- gates delivery, never cancels the collection.
class T3LaunchWhenStartedTest {
    @Test fun `keeps consuming the source below STARTED, it just doesn't deliver`() = runTest {
        val owner = FakeLifecycleOwner(LifecycleState.CREATED)
        val channel = Channel<Int>(Channel.UNLIMITED)
        val counter = Counter()
        val consumed = mutableListOf<Int>()

        launchWhenStarted(owner, backgroundScope, countedSource(channel, counter)) { consumed.add(it) }
        runCurrent()

        channel.trySend(1)
        channel.trySend(2)
        runCurrent()

        assertEquals(2, counter.value)
        assertEquals(emptyList<Int>(), consumed)
    }

    @Test fun `delivers new values once STARTED, without needing a restart`() = runTest {
        val owner = FakeLifecycleOwner(LifecycleState.CREATED)
        val channel = Channel<Int>(Channel.UNLIMITED)
        val counter = Counter()
        val consumed = mutableListOf<Int>()

        launchWhenStarted(owner, backgroundScope, countedSource(channel, counter)) { consumed.add(it) }
        runCurrent()
        channel.trySend(1)
        runCurrent()

        owner.moveTo(LifecycleState.STARTED)
        channel.trySend(2)
        runCurrent()

        assertEquals(listOf(2), consumed)
        assertEquals(2, counter.value)
    }

    @Test fun `never cancels the collection, so the counter keeps moving even after dropping below STARTED again`() = runTest {
        val owner = FakeLifecycleOwner(LifecycleState.STARTED)
        val channel = Channel<Int>(Channel.UNLIMITED)
        val counter = Counter()
        val consumed = mutableListOf<Int>()

        launchWhenStarted(owner, backgroundScope, countedSource(channel, counter)) { consumed.add(it) }
        runCurrent()
        channel.trySend(1)
        runCurrent()
        assertEquals(listOf(1), consumed)

        owner.moveTo(LifecycleState.CREATED)
        channel.trySend(2)
        runCurrent()

        assertEquals(2, counter.value)
        assertEquals(listOf(1), consumed)
    }
}
