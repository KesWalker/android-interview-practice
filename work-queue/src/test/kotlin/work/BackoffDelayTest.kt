package work

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BackoffDelayTest {

    @Test
    fun `delay doubles with each retry attempt`() {
        assertEquals(100L, backoffMillis(1, 100L))
        assertEquals(200L, backoffMillis(2, 100L))
        assertEquals(400L, backoffMillis(3, 100L))
        assertEquals(800L, backoffMillis(4, 100L))
    }
}
