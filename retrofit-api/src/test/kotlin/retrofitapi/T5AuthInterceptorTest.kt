package retrofitapi

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

// Task 5: a real OkHttp Interceptor, verified end to end against MockWebServer.
class T5AuthInterceptorTest {
    private val server = MockWebServer()

    @BeforeEach fun start() { server.start() }
    @AfterEach fun stop() { server.shutdown() }

    @Test fun `adds a bearer header built from the token provider`() {
        server.enqueue(MockResponse().setResponseCode(200).setBody("ok"))
        val client = OkHttpClient.Builder().addInterceptor(authHeaderInterceptor { "secret-token" }).build()

        client.newCall(Request.Builder().url(server.url("/ping")).build()).execute().use { response ->
            assertEquals(200, response.code)
        }

        val recorded = server.takeRequest()
        assertEquals("Bearer secret-token", recorded.getHeader("Authorization"))
    }

    @Test fun `calls the token provider fresh for every request`() {
        server.enqueue(MockResponse().setResponseCode(200))
        server.enqueue(MockResponse().setResponseCode(200))
        var token = "first-token"
        val client = OkHttpClient.Builder().addInterceptor(authHeaderInterceptor { token }).build()

        client.newCall(Request.Builder().url(server.url("/a")).build()).execute().close()
        token = "second-token"
        client.newCall(Request.Builder().url(server.url("/b")).build()).execute().close()

        assertEquals("Bearer first-token", server.takeRequest().getHeader("Authorization"))
        assertEquals("Bearer second-token", server.takeRequest().getHeader("Authorization"))
    }
}
