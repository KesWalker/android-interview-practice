package nothingany

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class T4UnwrapOrThrowTest {
    @Test fun `returns value from Success`() {
        assertEquals("data", unwrapOrThrow(Outcome.Success("data")))
    }

    @Test fun `returns Int from Success`() {
        assertEquals(42, unwrapOrThrow(Outcome.Success(42)))
    }

    @Test fun `throws RuntimeException for Failure`() {
        val ex = assertThrows<RuntimeException> {
            unwrapOrThrow(Outcome.Failure("went wrong"))
        }
        assertEquals("went wrong", ex.message)
    }
}
