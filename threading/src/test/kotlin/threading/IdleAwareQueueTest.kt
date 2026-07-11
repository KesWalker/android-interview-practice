package threading

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 5: MessageQueue.addIdleHandler -- fires only once the queue has nothing left to dispatch.
class IdleAwareQueueTest {
    @Test fun `fires the idle listener exactly once after all posted actions have run`() {
        val queue = IdleAwareQueue()
        val order = mutableListOf<String>()
        var idleCount = 0
        queue.post { order.add("a") }
        queue.post { order.add("b") }
        queue.addIdleListener {
            idleCount++
            order.add("idle")
        }

        queue.runToCompletion()

        assertEquals(listOf("a", "b", "idle"), order)
        assertEquals(1, idleCount)
    }

    @Test fun `does not fire while the queue still has pending work, even when an action posts more`() {
        val queue = IdleAwareQueue()
        val order = mutableListOf<String>()
        queue.post {
            order.add("first")
            queue.post { order.add("nested") }
        }
        queue.addIdleListener { order.add("idle") }

        queue.runToCompletion()

        assertEquals(listOf("first", "nested", "idle"), order)
    }

    @Test fun `multiple idle listeners each fire exactly once, in registration order`() {
        val queue = IdleAwareQueue()
        val order = mutableListOf<String>()
        queue.post { order.add("work") }
        queue.addIdleListener { order.add("first") }
        queue.addIdleListener { order.add("second") }

        queue.runToCompletion()

        assertEquals(listOf("work", "first", "second"), order)
    }

    @Test fun `fires immediately when nothing was ever posted`() {
        val queue = IdleAwareQueue()
        var fired = false
        queue.addIdleListener { fired = true }

        queue.runToCompletion()

        assertTrue(fired)
    }
}
