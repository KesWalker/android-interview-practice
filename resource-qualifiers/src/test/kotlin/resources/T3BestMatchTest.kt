package resources

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

// Task 3
class T3BestMatchTest {
    private val device = DeviceConfig(locale = "en", night = true, densityDpi = 320)

    @Test fun `only the default is available`() {
        val default = Qualifiers()
        assertEquals(default, bestMatch(listOf(default), device))
    }

    @Test fun `a locale match beats the default`() {
        val default = Qualifiers()
        val en = Qualifiers(locale = "en")
        assertEquals(en, bestMatch(listOf(default, en), device))
    }

    @Test fun `a contradicting locale is eliminated`() {
        val default = Qualifiers()
        val en = Qualifiers(locale = "en")
        val fr = Qualifiers(locale = "fr")
        assertEquals(en, bestMatch(listOf(default, en, fr), device))
    }

    @Test fun `a night match beats the default`() {
        val default = Qualifiers()
        val night = Qualifiers(night = true)
        assertEquals(night, bestMatch(listOf(default, night), device))
    }

    @Test fun `locale outranks night mode`() {
        val en = Qualifiers(locale = "en")
        val enNight = Qualifiers(locale = "en", night = true)
        assertEquals(enNight, bestMatch(listOf(en, enNight), device))
    }

    @Test fun `nothing more specific matches falls back to the default`() {
        val other = DeviceConfig(locale = "fr", night = false, densityDpi = 160)
        val default = Qualifiers()
        val en = Qualifiers(locale = "en")
        assertEquals(default, bestMatch(listOf(default, en), other))
    }

    @Test fun `no compatible candidate returns null`() {
        assertNull(bestMatch(listOf(Qualifiers(locale = "fr")), device))
    }
}
