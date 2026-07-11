package work

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class T1EnqueueOrderTest {

    @Test
    fun `jobs run in the order they were enqueued`() {
        val queue = WorkQueue()
        queue.enqueue(Job("a", 1) { true })
        queue.enqueue(Job("b", 1) { true })
        queue.enqueue(Job("c", 1) { true })

        assertEquals(listOf("a", "b", "c"), queue.pendingIds())

        queue.runNext()

        assertEquals(listOf("b", "c"), queue.pendingIds())
    }
}
