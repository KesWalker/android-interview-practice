package lifecyclecollect

import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 5: shareIn turns a cold flow into one shared, hot upstream.
class T5SharedTicksTest {
    @Test fun `starts producing the moment it's shared, before anyone collects`() = runTest {
        val startCounter = Counter()
        sharedTicks(backgroundScope, startCounter)
        runCurrent()

        assertEquals(1, startCounter.value)
    }

    @Test fun `replays the full sequence to every collector without re-running the upstream`() = runTest {
        val startCounter = Counter()
        val shared = sharedTicks(backgroundScope, startCounter)
        runCurrent()

        val first = mutableListOf<Int>()
        backgroundScope.launch { shared.collect { first.add(it) } }
        runCurrent()

        val second = mutableListOf<Int>()
        backgroundScope.launch { shared.collect { second.add(it) } }
        runCurrent()

        assertEquals(listOf(1, 2, 3), first)
        assertEquals(listOf(1, 2, 3), second)
        assertEquals(1, startCounter.value)
    }

    @Test fun `contrasts with a plain cold flow, which would have bumped the counter once per collection`() = runTest {
        val coldCounter = Counter()
        val cold = coldTicks(coldCounter)
        cold.collect { }
        cold.collect { }
        assertEquals(2, coldCounter.value)

        val sharedCounter = Counter()
        val shared = sharedTicks(backgroundScope, sharedCounter)
        runCurrent()
        backgroundScope.launch { shared.collect { } }
        backgroundScope.launch { shared.collect { } }
        runCurrent()

        assertEquals(1, sharedCounter.value)
    }
}
