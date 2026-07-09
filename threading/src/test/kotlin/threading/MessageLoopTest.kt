package threading

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 1: a FIFO queue drained on the caller's own thread.
class MessageLoopTest {
    @Test fun `runs actions in the order they were posted`() {
        val loop = MessageLoop()
        val order = mutableListOf<Int>()
        loop.post { order.add(1) }
        loop.post { order.add(2) }
        loop.post { order.add(3) }

        loop.runToCompletion()

        assertEquals(listOf(1, 2, 3), order)
    }

    @Test fun `an action posted while running goes to the back of the line, not immediately`() {
        val loop = MessageLoop()
        val order = mutableListOf<Int>()
        loop.post {
            order.add(1)
            loop.post { order.add(3) }
        }
        loop.post { order.add(2) }

        loop.runToCompletion()

        assertEquals(listOf(1, 2, 3), order)
    }

    @Test fun `does nothing when nothing was posted`() {
        val loop = MessageLoop()

        loop.runToCompletion()

        // no exception, nothing to assert beyond "it returned"
    }
}
