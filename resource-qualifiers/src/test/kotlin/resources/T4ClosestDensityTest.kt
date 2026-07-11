package resources

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 4
class T4ClosestDensityTest {
    @Test fun `picks the nearest bucket`() =
        assertEquals(
            Density.HDPI,
            closestDensity(listOf(Density.MDPI, Density.HDPI, Density.XXHDPI), deviceDpi = 320)
        )

    @Test fun `an exact match wins outright`() =
        assertEquals(
            Density.XHDPI,
            closestDensity(listOf(Density.MDPI, Density.XHDPI, Density.XXXHDPI), deviceDpi = 320)
        )

    @Test fun `a tie prefers the higher-dpi bucket`() =
        assertEquals(
            Density.XXHDPI,
            closestDensity(listOf(Density.XHDPI, Density.XXHDPI), deviceDpi = 400)
        )

    @Test fun `a single candidate is returned regardless of distance`() =
        assertEquals(Density.LDPI, closestDensity(listOf(Density.LDPI), deviceDpi = 1000))
}
