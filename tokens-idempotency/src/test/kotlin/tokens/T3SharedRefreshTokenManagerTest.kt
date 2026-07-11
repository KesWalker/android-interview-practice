package tokens

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.concurrent.atomic.AtomicInteger

// Task 3: cancellation-safe shared refresh.
class T3SharedRefreshTokenManagerTest {
    @Test fun `returns the refreshed token`() = runTest {
        val manager = SharedRefreshTokenManager(this) { "fresh-token" }
        assertEquals("fresh-token", manager.getToken())
    }

    @Test fun `two waiting callers share the same single refresh`() = runTest {
        val refreshCount = AtomicInteger(0)
        val manager = SharedRefreshTokenManager(this) {
            refreshCount.incrementAndGet()
            delay(50)
            "shared-token"
        }

        val a = async { manager.getToken() }
        val b = async { manager.getToken() }
        testScheduler.advanceUntilIdle()

        assertEquals("shared-token", a.await())
        assertEquals("shared-token", b.await())
        assertEquals(1, refreshCount.get())
    }

    @Test fun `cancelling one caller does not cancel the refresh a second caller is awaiting`() = runTest {
        val refreshCount = AtomicInteger(0)
        val manager = SharedRefreshTokenManager(this) {
            refreshCount.incrementAndGet()
            delay(100)
            "fresh-token"
        }

        val cancelled = launch { manager.getToken() }
        val survivor = async { manager.getToken() }
        testScheduler.runCurrent()

        cancelled.cancel()
        testScheduler.advanceUntilIdle()

        assertEquals("fresh-token", survivor.await())
        assertEquals(1, refreshCount.get())
    }
}
