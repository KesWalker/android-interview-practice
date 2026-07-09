package interceptors

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 1: running a list of interceptors in order.
class ChainExecutionTest {
    @Test fun `runs interceptors in order then the network call`() {
        val log = mutableListOf<String>()
        val first = Interceptor { chain ->
            log.add("first-before")
            val response = chain.proceed(chain.request)
            log.add("first-after")
            response
        }
        val second = Interceptor { chain ->
            log.add("second-before")
            val response = chain.proceed(chain.request)
            log.add("second-after")
            response
        }

        val response = executeChain(listOf(first, second), Request("/ping")) { req ->
            log.add("network")
            Response(200, "pong for ${req.path}")
        }

        assertEquals(listOf("first-before", "second-before", "network", "second-after", "first-after"), log)
        assertEquals(200, response.code)
        assertEquals("pong for /ping", response.body)
    }

    @Test fun `an interceptor can short-circuit without reaching later interceptors or the network`() {
        var secondCalled = false
        var networkCalled = false
        val shortCircuit = Interceptor { Response(403, "blocked") }
        val second = Interceptor { chain ->
            secondCalled = true
            chain.proceed(chain.request)
        }

        val response = executeChain(listOf(shortCircuit, second), Request("/secret")) {
            networkCalled = true
            Response(200, "ok")
        }

        assertEquals(403, response.code)
        assertEquals(false, secondCalled)
        assertEquals(false, networkCalled)
    }
}
