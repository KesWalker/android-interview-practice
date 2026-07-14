package retrofitapi

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

// Task 6: a real OkHttp Authenticator that refreshes on 401 and retries once.
class T6RefreshAuthenticatorTest {
    private val server = MockWebServer()

    @BeforeEach fun start() { server.start() }
    @AfterEach fun stop() { server.shutdown() }

    @Test fun `retries once with a refreshed token after a 401`() {
        server.enqueue(MockResponse().setResponseCode(401))
        server.enqueue(MockResponse().setResponseCode(200).setBody("ok"))
        val client = OkHttpClient.Builder()
            .authenticator(refreshingAuthenticator { "fresh-token" })
            .build()
        val request = Request.Builder()
            .url(server.url("/secure"))
            .header("Authorization", "Bearer stale-token")
            .build()

        client.newCall(request).execute().use { response ->
            assertEquals(200, response.code)
        }

        assertEquals(2, server.requestCount)
        server.takeRequest()
        val retried = server.takeRequest()
        assertEquals("Bearer fresh-token", retried.getHeader("Authorization"))
    }

    @Test fun `gives up after one retry instead of looping forever`() {
        server.enqueue(MockResponse().setResponseCode(401))
        server.enqueue(MockResponse().setResponseCode(401))
        val client = OkHttpClient.Builder()
            .authenticator(refreshingAuthenticator { "fresh-token" })
            .build()
        val request = Request.Builder()
            .url(server.url("/secure"))
            .header("Authorization", "Bearer stale-token")
            .build()

        client.newCall(request).execute().use { response ->
            assertEquals(401, response.code)
        }

        assertEquals(2, server.requestCount)
    }
}
