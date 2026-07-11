package permissions

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AfterBackgroundingTest {

    @Test
    fun `a OneTime grant reverts to Denied after a long background stint`() {
        val result = afterBackgrounding(LocationGrantMode.OneTime, wasLongBackground = true)
        assertEquals(LocationGrantMode.Denied, result)
    }

    @Test
    fun `a OneTime grant is unaffected by a short background stint`() {
        val result = afterBackgrounding(LocationGrantMode.OneTime, wasLongBackground = false)
        assertEquals(LocationGrantMode.OneTime, result)
    }

    @Test
    fun `an Always grant stays Always after a long background stint`() {
        val result = afterBackgrounding(LocationGrantMode.Always, wasLongBackground = true)
        assertEquals(LocationGrantMode.Always, result)
    }

    @Test
    fun `an Always grant stays Always after a short background stint`() {
        val result = afterBackgrounding(LocationGrantMode.Always, wasLongBackground = false)
        assertEquals(LocationGrantMode.Always, result)
    }
}
