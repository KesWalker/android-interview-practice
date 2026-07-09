package interceptors

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 4: refreshing and retrying once after a 401.
class RetryOnUnauthorizedTest {
    @Test fun `retries once with a refreshed token after a 401`() {
        var proceedCalls = 0
        val chain = object : Chain {
            override val request = Request("/private", headers = mapOf("Authorization" to "Bearer stale"))
            override fun proceed(request: Request): Response {
                proceedCalls++
                return if (request.headers["Authorization"] == "Bearer fresh") {
                    Response(200, "ok")
                } else {
                    Response(401, "unauthorized")
                }
            }
        }

        val response = retryOnUnauthorized { "fresh" }.intercept(chain)

        assertEquals(200, response.code)
        assertEquals(2, proceedCalls)
    }

    @Test fun `does not retry when the first response is already fine`() {
        var proceedCalls = 0
        val chain = object : Chain {
            override val request = Request("/public")
            override fun proceed(request: Request): Response {
                proceedCalls++
                return Response(200, "ok")
            }
        }

        val response = retryOnUnauthorized { "fresh" }.intercept(chain)

        assertEquals(200, response.code)
        assertEquals(1, proceedCalls)
    }
}
