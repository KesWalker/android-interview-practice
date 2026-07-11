package work

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class T5RestartBehaviorTest {

    @Test
    fun `START_STICKY always restarts with the intent never redelivered`() {
        assertEquals(
            RestartOutcome(recreated = true, intentRedelivered = false),
            restartBehavior(StartCommandFlag.STICKY, hadPendingStartIntent = true),
        )
        assertEquals(
            RestartOutcome(recreated = true, intentRedelivered = false),
            restartBehavior(StartCommandFlag.STICKY, hadPendingStartIntent = false),
        )
    }

    @Test
    fun `START_NOT_STICKY only restarts when a start intent was pending, and never redelivers`() {
        assertEquals(
            RestartOutcome(recreated = true, intentRedelivered = false),
            restartBehavior(StartCommandFlag.NOT_STICKY, hadPendingStartIntent = true),
        )
        assertEquals(
            RestartOutcome(recreated = false, intentRedelivered = false),
            restartBehavior(StartCommandFlag.NOT_STICKY, hadPendingStartIntent = false),
        )
    }

    @Test
    fun `START_REDELIVER_INTENT restarts and redelivers exactly when a start intent was pending`() {
        assertEquals(
            RestartOutcome(recreated = true, intentRedelivered = true),
            restartBehavior(StartCommandFlag.REDELIVER_INTENT, hadPendingStartIntent = true),
        )
        assertEquals(
            RestartOutcome(recreated = false, intentRedelivered = false),
            restartBehavior(StartCommandFlag.REDELIVER_INTENT, hadPendingStartIntent = false),
        )
    }
}
