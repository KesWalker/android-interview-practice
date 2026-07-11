package threading

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 4: Looper.quit() vs Looper.quitSafely() semantics.
class T4StoppableQueueTest {
    @Test fun `quit drops everything, even work that is already due`() {
        val queue = StoppableQueue()
        val ran = mutableListOf<Int>()
        queue.post { ran.add(1) }
        queue.postDelayed(1000) { ran.add(2) }

        queue.quit()

        assertEquals(emptyList<Int>(), ran)
    }

    @Test fun `quitSafely runs what is already due, then drops the rest`() {
        val queue = StoppableQueue()
        val ran = mutableListOf<Int>()
        queue.post { ran.add(1) }
        queue.postDelayed(50) { ran.add(2) }
        queue.postDelayed(1000) { ran.add(3) }

        queue.quitSafely(50)

        assertEquals(listOf(1, 2), ran)
    }

    @Test fun `quitSafely with nothing due yet runs nothing`() {
        val queue = StoppableQueue()
        val ran = mutableListOf<Int>()
        queue.postDelayed(500) { ran.add(1) }

        queue.quitSafely(0)

        assertEquals(emptyList<Int>(), ran)
    }

    @Test fun `quitSafely orders due actions by due time, not by when they were posted`() {
        val queue = StoppableQueue()
        val order = mutableListOf<Int>()
        queue.postDelayed(20) { order.add(1) }
        queue.postDelayed(10) { order.add(2) }
        queue.post { order.add(3) }

        queue.quitSafely(20)

        assertEquals(listOf(3, 2, 1), order)
    }
}
