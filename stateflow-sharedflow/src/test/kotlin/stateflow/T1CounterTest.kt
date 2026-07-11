package stateflow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 1: MutableStateFlow with an atomic update under concurrency.
class T1CounterTest {
    @Test fun `starts at zero`() = runTest {
        assertEquals(0, Counter().count.value)
    }

    @Test fun `counts a single increment`() = runTest {
        val counter = Counter()
        counter.increment()
        assertEquals(1, counter.count.value)
    }

    @Test fun `survives many concurrent increments without losing updates`() = runTest {
        val counter = Counter()
        val jobs = List(50) {
            launch(Dispatchers.Default) {
                repeat(1000) { counter.increment() }
            }
        }
        jobs.forEach { it.join() }

        assertEquals(50_000, counter.count.value)
    }
}
