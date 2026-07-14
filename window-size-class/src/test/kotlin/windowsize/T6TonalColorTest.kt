package windowsize

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 6: derive a tonal-palette color from a seed color and a tone level.
class T6TonalColorTest {
    private val seed = HslColor(hue = 265.0, saturation = 0.6, lightness = 0.4)

    @Test fun `mid tone keeps the seed's hue and saturation, scales lightness`() {
        val result = tonalColor(seed, tone = 50)
        assertEquals(HslColor(hue = 265.0, saturation = 0.6, lightness = 0.5), result)
    }

    @Test fun `tone 0 is black, saturation is forced to zero`() {
        val result = tonalColor(seed, tone = 0)
        assertEquals(HslColor(hue = 265.0, saturation = 0.0, lightness = 0.0), result)
    }

    @Test fun `tone 100 is white, saturation is forced to zero`() {
        val result = tonalColor(seed, tone = 100)
        assertEquals(HslColor(hue = 265.0, saturation = 0.0, lightness = 1.0), result)
    }

    @Test fun `out-of-range tone is clamped before use`() {
        val tooHigh = tonalColor(seed, tone = 150)
        assertEquals(HslColor(hue = 265.0, saturation = 0.0, lightness = 1.0), tooHigh)

        val tooLow = tonalColor(seed, tone = -20)
        assertEquals(HslColor(hue = 265.0, saturation = 0.0, lightness = 0.0), tooLow)
    }
}
