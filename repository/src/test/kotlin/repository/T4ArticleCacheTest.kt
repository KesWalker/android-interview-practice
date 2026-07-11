package repository

import java.util.concurrent.CountDownLatch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 4: guarding a repository's in-memory cache against lost updates.
class T4ArticleCacheTest {
    @Test fun `a single caller records hits correctly`() = runBlocking {
        val cache = ArticleCache()

        repeat(5) { cache.recordHit("home") }

        assertEquals(5, cache.hitsFor("home"))
    }

    @Test fun `many threads recording the same id lose no updates`() {
        val cache = ArticleCache()
        val threads = 20
        val hitsPerThread = 500
        val start = CountDownLatch(1)
        val done = CountDownLatch(threads)

        repeat(threads) {
            Thread {
                start.await()
                runBlocking { repeat(hitsPerThread) { cache.recordHit("home") } }
                done.countDown()
            }.start()
        }

        start.countDown()
        done.await()

        runBlocking {
            assertEquals(threads * hitsPerThread, cache.hitsFor("home"))
        }
    }
}
