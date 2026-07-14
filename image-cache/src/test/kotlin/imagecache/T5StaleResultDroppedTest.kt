package imagecache

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

// Task 5: a stale in-flight result is dropped once the target is recycled.
class T5StaleResultDroppedTest {
    @Test fun `stale result is dropped when the target is rebound before it finishes`() = runTest {
        val target = ImageTarget()
        var firstResult: ByteArray? = "not set".toByteArray()

        launch {
            firstResult = loadInto(target, "first") { delay(100); "first-bytes".toByteArray() }
        }
        runCurrent() // let the load start and suspend inside delay
        target.currentRequestKey = "second" // cell recycled for different content

        advanceUntilIdle()

        assertNull(firstResult)
    }

    @Test fun `result is returned when the target is not rebound`() = runTest {
        val target = ImageTarget()
        var result: ByteArray? = null

        launch {
            result = loadInto(target, "only") { delay(100); "bytes".toByteArray() }
        }
        advanceUntilIdle()

        assertArrayEquals("bytes".toByteArray(), result)
    }
}
