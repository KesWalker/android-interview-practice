package offline

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ClassifySyncOutcomeTest {

    @Test
    fun `a sync block that completes normally reports success`() {
        assertEquals(SyncOutcome.Success, classifySyncOutcome { })
    }

    @Test
    fun `a transient failure reports retry`() {
        val outcome = classifySyncOutcome { throw TransientSyncException("network blip") }

        assertEquals(SyncOutcome.Retry, outcome)
    }

    @Test
    fun `any other exception reports failure`() {
        val outcome = classifySyncOutcome { throw IllegalStateException("corrupt payload") }

        assertEquals(SyncOutcome.Failure, outcome)
    }
}
