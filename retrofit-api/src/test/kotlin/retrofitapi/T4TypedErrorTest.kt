package retrofitapi

import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

// Task 4: mapping a 404 (and other failures) to a typed result instead of a
// thrown exception, plus the Response<T> vs bare-T contrast.
class T4TypedErrorTest {
    private val server = MockWebServer()

    @BeforeEach fun start() { server.start() }
    @AfterEach fun stop() { server.shutdown() }

    @Test fun `maps a 404 to NotFound`() {
        server.enqueue(MockResponse().setResponseCode(404))
        val api = createUserApi(server.url("/").toString())

        val result = runBlocking { fetchUserResult(api, 99) }

        assertEquals(UserResult.NotFound(99), result)
    }

    @Test fun `maps a successful response to Found`() {
        server.enqueue(MockResponse().setResponseCode(200).setBody("""{"id":5,"name":"Lin","active":true}"""))
        val api = createUserApi(server.url("/").toString())

        val result = runBlocking { fetchUserResult(api, 5) }

        assertEquals(UserResult.Found(User(5, "Lin", true)), result)
    }

    @Test fun `maps a 500 to Failed with its code`() {
        server.enqueue(MockResponse().setResponseCode(500))
        val api = createUserApi(server.url("/").toString())

        val result = runBlocking { fetchUserResult(api, 5) }

        assertEquals(UserResult.Failed(500), result)
    }

    @Test fun `getUserResponse never throws on a 404, unlike the bare-User call`() {
        server.enqueue(MockResponse().setResponseCode(404))
        val api = createUserApi(server.url("/").toString())

        val response = runBlocking { api.getUserResponse(42) }

        assertFalse(response.isSuccessful)
        assertEquals(404, response.code())
    }
}
