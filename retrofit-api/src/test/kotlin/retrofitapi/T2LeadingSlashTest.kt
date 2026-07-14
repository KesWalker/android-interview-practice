package retrofitapi

import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

// Task 2: the leading-slash baseUrl gotcha.
class T2LeadingSlashTest {
    private val server = MockWebServer()

    @BeforeEach fun start() { server.start() }
    @AfterEach fun stop() { server.shutdown() }

    @Test fun `baseUrl missing a trailing slash still builds and resolves`() {
        server.enqueue(MockResponse().setResponseCode(200).setBody("""{"id":3,"name":"Robin","active":true}"""))
        val rawBaseUrl = server.url("/api/").toString().removeSuffix("/")

        val api = createLegacyApi(rawBaseUrl)
        val result = runBlocking { api.getUser(3) }

        assertEquals(User(3, "Robin", true), result)
    }

    @Test fun `a leading slash on the endpoint path drops baseUrl's own path segment`() {
        server.enqueue(MockResponse().setResponseCode(200).setBody("""{"id":3,"name":"Robin","active":true}"""))
        val rawBaseUrl = server.url("/api/").toString().removeSuffix("/")

        val api = createLegacyApi(rawBaseUrl)
        runBlocking { api.getUser(3) }

        // LegacyApi.getUser is @GET("/v2/users/{id}") - the leading slash makes
        // this an absolute path, so "/api" from baseUrl never appears here.
        val recorded = server.takeRequest()
        assertEquals("/v2/users/3", recorded.path)
    }

    @Test fun `baseUrl that already ends with a slash still works`() {
        server.enqueue(MockResponse().setResponseCode(200).setBody("""{"id":4,"name":"Sam","active":true}"""))
        val api = createLegacyApi(server.url("/").toString())

        val result = runBlocking { api.getUser(4) }

        assertEquals(User(4, "Sam", true), result)
    }
}
