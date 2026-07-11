package interceptors

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 5: serving a stale cache only when offline.
class T5OfflineFallbackInterceptorTest {
    @Test fun `offline with a cache hit returns the cached response without proceeding`() {
        var proceedCalls = 0
        val cache = mapOf("/pinned" to Response(200, "cached body"))
        val chain = object : Chain {
            override val request = Request("/pinned")
            override fun proceed(request: Request): Response {
                proceedCalls++
                return Response(200, "fresh body")
            }
        }

        val response = offlineFallbackInterceptor(cache) { true }.intercept(chain)

        assertEquals("cached body", response.body)
        assertEquals(0, proceedCalls)
    }

    @Test fun `offline with a cache miss falls through to proceed`() {
        val cache = emptyMap<String, Response>()
        var proceedCalls = 0
        val chain = object : Chain {
            override val request = Request("/uncached")
            override fun proceed(request: Request): Response {
                proceedCalls++
                return Response(200, "fresh body")
            }
        }

        val response = offlineFallbackInterceptor(cache) { true }.intercept(chain)

        assertEquals("fresh body", response.body)
        assertEquals(1, proceedCalls)
    }

    @Test fun `online always proceeds even when a cache entry exists`() {
        val cache = mapOf("/pinned" to Response(200, "cached body"))
        var proceedCalls = 0
        val chain = object : Chain {
            override val request = Request("/pinned")
            override fun proceed(request: Request): Response {
                proceedCalls++
                return Response(200, "fresh body")
            }
        }

        val response = offlineFallbackInterceptor(cache) { false }.intercept(chain)

        assertEquals("fresh body", response.body)
        assertEquals(1, proceedCalls)
    }
}
