package doze

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 6: exponential backoff, doubling per attempt, capped.
class T6BackoffForAttemptTest {
    @Test fun `first attempt waits the base delay`() {
        assertEquals(1_000L, backoffForAttempt(1, baseMillis = 1_000L, capMillis = 60_000L))
    }

    @Test fun `delay doubles with each later attempt`() {
        assertEquals(4_000L, backoffForAttempt(3, baseMillis = 1_000L, capMillis = 60_000L))
    }

    @Test fun `delay never exceeds the cap`() {
        assertEquals(5_000L, backoffForAttempt(10, baseMillis = 1_000L, capMillis = 5_000L))
    }
}
