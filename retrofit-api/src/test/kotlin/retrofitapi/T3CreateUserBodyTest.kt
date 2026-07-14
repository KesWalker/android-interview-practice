package retrofitapi

import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

// Task 3: a @Body POST with a @Serializable request model.
class T3CreateUserBodyTest {
    private val server = MockWebServer()

    @BeforeEach fun start() { server.start() }
    @AfterEach fun stop() { server.shutdown() }

    @Test fun `POSTs the body as JSON and returns the decoded response`() {
        server.enqueue(MockResponse().setResponseCode(201).setBody("""{"id":9,"name":"Ada","active":true}"""))
        val api = createCreateUserApi(server.url("/").toString())

        val created = runBlocking { api.createUser(NewUserRequest("Ada", "ada@example.com")) }

        assertEquals(User(9, "Ada", true), created)

        val recorded = server.takeRequest()
        assertEquals("POST", recorded.method)
        assertTrue(recorded.getHeader("Content-Type")?.contains("application/json") == true)
        val sentBody = Json.decodeFromString<NewUserRequest>(recorded.body.readUtf8())
        assertEquals(NewUserRequest("Ada", "ada@example.com"), sentBody)
    }
}
