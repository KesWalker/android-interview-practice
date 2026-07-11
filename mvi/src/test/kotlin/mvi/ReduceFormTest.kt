package mvi

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 5: a reducer stays pure by taking its timestamp from the intent, never the wall clock.
class ReduceFormTest {
    @Test fun `submittedAt is taken from the intent's timestamp`() {
        val result = reduceForm(FormState(), FormIntent.Submit(timestampMs = 1_000L))
        assertEquals(1_000L, result.submittedAt)
    }

    @Test fun `calling reduceForm twice with the identical state and intent produces equal results`() {
        val state = FormState()
        val intent = FormIntent.Submit(timestampMs = 42_000L)

        val first = reduceForm(state, intent)
        val second = reduceForm(state, intent)

        assertEquals(first, second)
    }

    @Test fun `submittedAt always matches the intent's timestamp, overwriting any prior value`() {
        val result = reduceForm(FormState(submittedAt = 5L), FormIntent.Submit(timestampMs = 99_999L))
        assertEquals(99_999L, result.submittedAt)
    }
}
