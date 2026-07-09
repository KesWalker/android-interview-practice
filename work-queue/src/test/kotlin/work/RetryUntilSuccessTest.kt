package work

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RetryUntilSuccessTest {

    @Test
    fun `retries a failing action until it succeeds`() {
        var calls = 0
        val queue = WorkQueue()
        queue.enqueue(
            Job(id = "download", maxAttempts = 5) {
                calls++
                calls >= 3
            }
        )

        val result = queue.runNext()

        assertEquals(JobResult(id = "download", attempts = 3, succeeded = true), result)
        assertEquals(3, calls)
    }
}
