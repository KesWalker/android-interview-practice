package urlresolution

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 5: mapping a raw outcome to the sealed ApiResult.
class T5MapOutcomeTest {
    @Test fun `2xx status maps to Success with the data`() {
        assertEquals(Success("payload"), toApiResult(HttpResponse(200), "payload"))
    }

    @Test fun `299 is still success, 300 is not`() {
        assertEquals(Success("x"), toApiResult(HttpResponse(299), "x"))
        assertEquals(HttpException(300), toApiResult(HttpResponse(300), "x"))
    }

    @Test fun `non-2xx status maps to an HttpException carrying the code`() {
        assertEquals(HttpException(404), toApiResult(HttpResponse(404), "x"))
    }

    @Test fun `an IO failure maps to a NetworkError carrying the cause`() {
        val cause = RuntimeException("timeout")
        assertEquals(NetworkError(cause), toApiResult(IoFailure(cause), "x"))
    }
}
