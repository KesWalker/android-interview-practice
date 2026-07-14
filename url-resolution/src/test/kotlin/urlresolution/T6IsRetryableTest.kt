package urlresolution

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 6: retry policy, timeouts/429/5xx retryable, permanent 4xx is not.
class T6IsRetryableTest {
    @Test fun `success is never retryable`() {
        assertFalse(isRetryable(Success("x")))
    }

    @Test fun `a network error is retryable`() {
        assertTrue(isRetryable(NetworkError(RuntimeException("timeout"))))
    }

    @Test fun `429 and 5xx are retryable`() {
        assertTrue(isRetryable(HttpException(429)))
        assertTrue(isRetryable(HttpException(503)))
    }

    @Test fun `other 4xx codes are not retryable`() {
        assertFalse(isRetryable(HttpException(400)))
        assertFalse(isRetryable(HttpException(404)))
    }
}
