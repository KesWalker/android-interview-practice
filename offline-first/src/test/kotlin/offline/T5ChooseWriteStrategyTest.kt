package offline

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class T5ChooseWriteStrategyTest {

    @Test
    fun `a write that must not silently fail picks online-only even when it also wants instant UI`() {
        val characteristics = WriteCharacteristics(mustNotSilentlyFail = true, needsInstantUi = true)

        assertEquals(WriteStrategy.ONLINE_ONLY, chooseWriteStrategy(characteristics))
    }

    @Test
    fun `a write needing instant UI but tolerant of eventual sync picks lazy`() {
        val characteristics = WriteCharacteristics(mustNotSilentlyFail = false, needsInstantUi = true)

        assertEquals(WriteStrategy.LAZY, chooseWriteStrategy(characteristics))
    }

    @Test
    fun `a write with neither requirement picks queued`() {
        val characteristics = WriteCharacteristics(mustNotSilentlyFail = false, needsInstantUi = false)

        assertEquals(WriteStrategy.QUEUED, chooseWriteStrategy(characteristics))
    }
}
