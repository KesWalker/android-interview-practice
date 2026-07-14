package imagecache

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 4: N concurrent requests for the same key trigger only ONE load.
class T4RequestCoalescingTest {
    @Test fun `concurrent requests for the same key trigger only one load`() = runTest {
        var callCount = 0
        val coalescer = ImageRequestCoalescer { key ->
            callCount++
            delay(100)
            "bytes-for-$key".toByteArray()
        }

        val r1 = async { coalescer.get("a") }
        val r2 = async { coalescer.get("a") }
        val r3 = async { coalescer.get("a") }
        val results = listOf(r1, r2, r3).map { it.await() }

        assertEquals(1, callCount)
        results.forEach { assertArrayEquals("bytes-for-a".toByteArray(), it) }
    }

    @Test fun `different keys each trigger their own load`() = runTest {
        var callCount = 0
        val coalescer = ImageRequestCoalescer { key ->
            callCount++
            delay(100)
            "bytes-for-$key".toByteArray()
        }

        val a = async { coalescer.get("a") }
        val b = async { coalescer.get("b") }
        assertArrayEquals("bytes-for-a".toByteArray(), a.await())
        assertArrayEquals("bytes-for-b".toByteArray(), b.await())
        assertEquals(2, callCount)
    }

    @Test fun `a new call after the first load finishes starts a fresh load`() = runTest {
        var callCount = 0
        val coalescer = ImageRequestCoalescer { key ->
            callCount++
            delay(100)
            "bytes-for-$key".toByteArray()
        }

        coalescer.get("a")
        coalescer.get("a")

        assertEquals(2, callCount)
    }
}
