package tokens

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.util.concurrent.atomic.AtomicInteger

// Task 2: single-flight refresh under concurrency (Mutex + re-check).
class T2SingleFlightTokenManagerTest {
    @Test fun `returns the refreshed token`() = runTest {
        val manager = SingleFlightTokenManager { "fresh-token" }
        assertEquals("fresh-token", manager.getToken())
    }

    @Test fun `caches the token so a second call does not refresh again`() = runTest {
        var refreshCount = 0
        val manager = SingleFlightTokenManager {
            refreshCount++
            "token-$refreshCount"
        }
        manager.getToken()
        val second = manager.getToken()
        assertEquals("token-1", second)
        assertEquals(1, refreshCount)
    }

    @Test fun `twenty concurrent callers still trigger exactly one refresh`() = runTest {
        val refreshCount = AtomicInteger(0)
        val manager = SingleFlightTokenManager {
            refreshCount.incrementAndGet()
            delay(100)
            "shared-token"
        }

        val results = mutableListOf<String>()
        repeat(20) {
            launch { results.add(manager.getToken()) }
        }
        testScheduler.advanceUntilIdle()

        assertEquals(1, refreshCount.get())
        assertEquals(20, results.size)
        assertTrue(results.all { it == "shared-token" })
    }
}
