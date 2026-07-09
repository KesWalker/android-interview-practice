package interceptors

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 2: injecting an auth header before continuing.
class AuthInterceptorTest {
    @Test fun `attaches a bearer token header before continuing`() {
        var seenRequest: Request? = null
        val chain = object : Chain {
            override val request = Request("/me")
            override fun proceed(request: Request): Response {
                seenRequest = request
                return Response(200, "ok")
            }
        }

        val response = authInterceptor { "abc123" }.intercept(chain)

        assertEquals("Bearer abc123", seenRequest?.headers?.get("Authorization"))
        assertEquals(200, response.code)
    }

    @Test fun `uses a fresh token on every call`() {
        var tokenCalls = 0
        var lastSeen: Request? = null
        val chain = object : Chain {
            override val request = Request("/me")
            override fun proceed(request: Request): Response {
                lastSeen = request
                return Response(200, "ok")
            }
        }
        val interceptor = authInterceptor {
            tokenCalls++
            "token-$tokenCalls"
        }

        interceptor.intercept(chain)
        interceptor.intercept(chain)

        assertEquals("Bearer token-2", lastSeen?.headers?.get("Authorization"))
    }
}
