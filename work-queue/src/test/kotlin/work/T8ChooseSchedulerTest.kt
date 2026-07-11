package work

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class T8ChooseSchedulerTest {

    @Test
    fun `exact-time work picks ALARM_MANAGER regardless of other flags`() {
        assertEquals(
            SchedulerChoice.ALARM_MANAGER,
            chooseScheduler(WorkCharacteristics(needsExactTime = true, mustSurviveProcessDeath = false)),
        )
        assertEquals(
            SchedulerChoice.ALARM_MANAGER,
            chooseScheduler(WorkCharacteristics(needsExactTime = true, mustSurviveProcessDeath = true)),
        )
    }

    @Test
    fun `work that must survive process death but has no exact-time need picks WORK_MANAGER`() {
        assertEquals(
            SchedulerChoice.WORK_MANAGER,
            chooseScheduler(WorkCharacteristics(needsExactTime = false, mustSurviveProcessDeath = true)),
        )
    }

    @Test
    fun `work needing neither picks COROUTINE`() {
        assertEquals(
            SchedulerChoice.COROUTINE,
            chooseScheduler(WorkCharacteristics(needsExactTime = false, mustSurviveProcessDeath = false)),
        )
    }
}
