package offline

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class T2PendingWriteQueueTest {

    @Test
    fun `enqueue adds items and tracks size`() {
        val queue = PendingWriteQueue<String>()

        queue.enqueue("create-note-1")
        queue.enqueue("create-note-2")
        queue.enqueue("delete-note-3")

        assertEquals(3, queue.size())
    }

    @Test
    fun `drain returns items in FIFO order and empties the queue`() {
        val queue = PendingWriteQueue<String>()
        queue.enqueue("a")
        queue.enqueue("b")
        queue.enqueue("c")

        val drained = queue.drain()

        assertEquals(listOf("a", "b", "c"), drained)
        assertEquals(0, queue.size())
    }

    @Test
    fun `draining an empty queue returns an empty list`() {
        val queue = PendingWriteQueue<String>()

        assertTrue(queue.drain().isEmpty())
    }

    @Test
    fun `draining twice returns items only once`() {
        val queue = PendingWriteQueue<Int>()
        queue.enqueue(1)
        queue.enqueue(2)

        val first = queue.drain()
        val second = queue.drain()

        assertEquals(listOf(1, 2), first)
        assertTrue(second.isEmpty())
    }
}
