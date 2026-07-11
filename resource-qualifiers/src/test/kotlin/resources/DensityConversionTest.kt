package resources

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 6
class DensityConversionTest {
    @Test fun `48dp on xhdpi is 96px`() =
        assertEquals(96f, dpToPx(48f, densityDpi = 320), 0.01f)

    @Test fun `16dp on mdpi is unchanged at 16px`() =
        assertEquals(16f, dpToPx(16f, densityDpi = 160), 0.01f)

    @Test fun `spToPx applies the same dpi scaling as dpToPx when fontScale is 1`() =
        assertEquals(dpToPx(48f, densityDpi = 320), spToPx(48f, densityDpi = 320, fontScale = 1f), 0.01f)

    @Test fun `spToPx additionally multiplies by fontScale`() =
        assertEquals(96f * 1.3f, spToPx(48f, densityDpi = 320, fontScale = 1.3f), 0.01f)

    @Test fun `dpToPx ignores fontScale entirely`() =
        assertEquals(96f, dpToPx(48f, densityDpi = 320), 0.01f)
}
