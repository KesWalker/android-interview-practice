package channels

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class T4ConflatedLatestTest {
    @Test fun `returns last sent value`() = runTest {
        assertEquals(5, conflatedLatest(5))
    }

    @Test fun `single value`() = runTest {
        assertEquals(1, conflatedLatest(1))
    }

    @Test fun `large range returns last`() = runTest {
        assertEquals(100, conflatedLatest(100))
    }
}
