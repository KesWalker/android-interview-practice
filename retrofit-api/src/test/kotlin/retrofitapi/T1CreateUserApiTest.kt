package retrofitapi

import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

// Task 1: a real Retrofit client, built against a real (local) server.
class T1CreateUserApiTest {
    private val server = MockWebServer()

    @BeforeEach fun start() { server.start() }
    @AfterEach fun stop() { server.shutdown() }

    @Test fun `fetches and decodes a user, sending the query param`() {
        server.enqueue(MockResponse().setResponseCode(200).setBody("""{"id":7,"name":"Grace","active":true}"""))
        val api = createUserApi(server.url("/").toString())

        val user = runBlocking { api.getUser(7, verbose = true) }

        assertEquals(User(7, "Grace", true), user)
        val recorded = server.takeRequest()
        assertEquals("/users/7?verbose=true", recorded.path)
    }

    @Test fun `verbose defaults to false when not passed`() {
        server.enqueue(MockResponse().setResponseCode(200).setBody("""{"id":1,"name":"Ada","active":false}"""))
        val api = createUserApi(server.url("/").toString())

        val user = runBlocking { api.getUser(1) }

        assertEquals(User(1, "Ada", false), user)
        val recorded = server.takeRequest()
        assertEquals("/users/1?verbose=false", recorded.path)
    }
}
