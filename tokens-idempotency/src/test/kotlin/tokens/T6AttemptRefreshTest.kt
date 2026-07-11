package tokens

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

// Task 6: distinguishing a 401 on refresh from any other failure.
class T6AttemptRefreshTest {
    @Test fun `a successful refresh yields Refreshed with the new token`() = runTest {
        val outcome = attemptRefresh { "new-token" }
        assertEquals(RefreshOutcome.Refreshed("new-token"), outcome)
    }

    @Test fun `a 401 HttpException yields SessionExpired without throwing`() = runTest {
        val outcome = attemptRefresh { throw HttpException(401) }
        assertEquals(RefreshOutcome.SessionExpired, outcome)
    }

    @Test fun `any other exception propagates out of attemptRefresh unchanged`() = runTest {
        val thrown = try {
            attemptRefresh { throw HttpException(500) }
            null
        } catch (e: HttpException) {
            e
        }

        assertNotNull(thrown)
        assertEquals(500, thrown?.code)
    }
}
