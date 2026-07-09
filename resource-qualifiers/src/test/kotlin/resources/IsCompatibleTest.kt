package resources

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 2
class IsCompatibleTest {
    private val device = DeviceConfig(locale = "en", night = false, densityDpi = 320)

    @Test fun `default qualifiers always compatible`() =
        assertTrue(isCompatible(Qualifiers(), device))

    @Test fun `matching locale is compatible`() =
        assertTrue(isCompatible(Qualifiers(locale = "en"), device))

    @Test fun `different locale is not compatible`() =
        assertFalse(isCompatible(Qualifiers(locale = "fr"), device))

    @Test fun `matching night flag is compatible`() =
        assertTrue(isCompatible(Qualifiers(night = false), device))

    @Test fun `different night flag is not compatible`() =
        assertFalse(isCompatible(Qualifiers(night = true), device))

    @Test fun `a density qualifier is never a contradiction`() =
        assertTrue(isCompatible(Qualifiers(density = Density.LDPI), device))
}
