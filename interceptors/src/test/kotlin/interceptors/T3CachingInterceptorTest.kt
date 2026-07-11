package interceptors

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 3: serving from a cache instead of continuing.
class T3CachingInterceptorTest {
    @Test fun `serves a cached response without continuing`() {
        var proceedCalls = 0
        val cache = mutableMapOf("/pinned" to Response(200, "cached body"))
        val chain = object : Chain {
            override val request = Request("/pinned")
            override fun proceed(request: Request): Response {
                proceedCalls++
                return Response(200, "fresh body")
            }
        }

        val response = cachingInterceptor(cache).intercept(chain)

        assertEquals("cached body", response.body)
        assertEquals(0, proceedCalls)
    }

    @Test fun `continues and stores the response when nothing is cached yet`() {
        val cache = mutableMapOf<String, Response>()
        val chain = object : Chain {
            override val request = Request("/fresh")
            override fun proceed(request: Request): Response = Response(200, "brand new")
        }

        val response = cachingInterceptor(cache).intercept(chain)

        assertEquals("brand new", response.body)
        assertEquals(Response(200, "brand new"), cache["/fresh"])
    }
}
