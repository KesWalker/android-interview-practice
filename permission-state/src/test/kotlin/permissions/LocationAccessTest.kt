package permissions

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LocationAccessTest {

    @Test
    fun `fine granted returns FINE`() {
        val result = locationAccess(LocationRequestResult(fineGranted = true, coarseGranted = true))
        assertEquals(LocationPrecision.FINE, result)
    }

    @Test
    fun `fine denied but coarse granted returns COARSE`() {
        val result = locationAccess(LocationRequestResult(fineGranted = false, coarseGranted = true))
        assertEquals(LocationPrecision.COARSE, result)
    }

    @Test
    fun `both denied returns DENIED`() {
        val result = locationAccess(LocationRequestResult(fineGranted = false, coarseGranted = false))
        assertEquals(LocationPrecision.DENIED, result)
    }
}
