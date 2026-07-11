package flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 6: conflate drops stale values instead of buffering or backpressuring.
class T6ConflatedTicksTest {
    @Test fun `a slow collector sees fewer values than were emitted, ending on the last one`() = runTest {
        val emittedCount = 20
        val source = flow {
            for (i in 1..emittedCount) {
                emit(i)
            }
        }

        val collected = mutableListOf<Int>()
        conflatedTicks(source).collect { value ->
            delay(10)
            collected.add(value)
        }

        assertTrue(collected.size < emittedCount)
        assertEquals(emittedCount, collected.last())
    }

    @Test fun `a single-value source is delivered unchanged`() = runTest {
        val source = flow { emit(42) }

        val collected = mutableListOf<Int>()
        conflatedTicks(source).collect { collected.add(it) }

        assertEquals(listOf(42), collected)
    }
}
