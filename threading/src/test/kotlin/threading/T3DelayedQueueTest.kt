package threading

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 3: schedule-by-time ordering, like Handler.postDelayed.
class T3DelayedQueueTest {
    @Test fun `runs due actions earliest target time first`() {
        val queue = DelayedQueue()
        val order = mutableListOf<String>()
        queue.schedule(300) { order.add("c") }
        queue.schedule(100) { order.add("a") }
        queue.schedule(200) { order.add("b") }

        queue.runUpTo(300)

        assertEquals(listOf("a", "b", "c"), order)
    }

    @Test fun `leaves actions that are not due yet pending for a later run`() {
        val queue = DelayedQueue()
        val order = mutableListOf<String>()
        queue.schedule(100) { order.add("a") }
        queue.schedule(500) { order.add("b") }

        queue.runUpTo(100)
        assertEquals(listOf("a"), order)

        queue.runUpTo(500)
        assertEquals(listOf("a", "b"), order)
    }

    @Test fun `keeps posting order for actions due at the same time`() {
        val queue = DelayedQueue()
        val order = mutableListOf<Int>()
        queue.schedule(50) { order.add(1) }
        queue.schedule(50) { order.add(2) }
        queue.schedule(50) { order.add(3) }

        queue.runUpTo(50)

        assertEquals(listOf(1, 2, 3), order)
    }

    @Test fun `a due action only runs once, not again on the next run`() {
        val queue = DelayedQueue()
        val order = mutableListOf<Int>()
        queue.schedule(10) { order.add(1) }

        queue.runUpTo(10)
        queue.runUpTo(20)

        assertEquals(listOf(1), order)
    }
}
