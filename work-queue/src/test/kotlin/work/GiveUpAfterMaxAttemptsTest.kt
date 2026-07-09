package work

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GiveUpAfterMaxAttemptsTest {

    @Test
    fun `gives up once max attempts is reached`() {
        var calls = 0
        val queue = WorkQueue()
        queue.enqueue(
            Job(id = "upload", maxAttempts = 4) {
                calls++
                false
            }
        )

        val result = queue.runNext()

        assertEquals(JobResult(id = "upload", attempts = 4, succeeded = false), result)
        assertEquals(4, calls)
        assertEquals(emptyList<String>(), queue.pendingIds())
    }
}
