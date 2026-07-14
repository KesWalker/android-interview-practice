package semantics

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 7: waiting for real quiescence instead of sleeping.
class T7WaitForIdleTest {
    @Test fun `drains a queue whose actions enqueue more actions`() {
        val log = mutableListOf<String>()
        val queue = mutableListOf<() -> Unit>()
        queue.add {
            log.add("a")
            queue.add { log.add("b") }
            queue.add { log.add("c") }
        }

        val steps = waitForIdle(queue, maxSteps = 10)

        assertEquals(3, steps)
        assertEquals(listOf("a", "b", "c"), log)
        assertTrue(queue.isEmpty())
    }

    @Test fun `an already-empty queue is idle in zero steps`() {
        assertEquals(0, waitForIdle(mutableListOf(), maxSteps = 10))
    }

    @Test fun `throws instead of looping forever when the queue never settles`() {
        val queue = mutableListOf<() -> Unit>()
        lateinit var reQueue: () -> Unit
        reQueue = { queue.add(reQueue) }
        queue.add(reQueue)

        val ex = assertThrows(IllegalStateException::class.java) {
            waitForIdle(queue, maxSteps = 5)
        }

        assertEquals("did not reach idle after 5 steps", ex.message)
    }
}
