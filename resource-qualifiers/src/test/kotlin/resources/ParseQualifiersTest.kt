package resources

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 1
class ParseQualifiersTest {
    @Test fun `no qualifiers`() =
        assertEquals(Qualifiers(), parseQualifiers("values"))

    @Test fun `locale only`() =
        assertEquals(Qualifiers(locale = "en"), parseQualifiers("values-en"))

    @Test fun `night only`() =
        assertEquals(Qualifiers(night = true), parseQualifiers("values-night"))

    @Test fun `density only`() =
        assertEquals(Qualifiers(density = Density.XHDPI), parseQualifiers("drawable-xhdpi"))

    @Test fun `locale night and density together`() =
        assertEquals(
            Qualifiers(locale = "en", night = true, density = Density.HDPI),
            parseQualifiers("drawable-en-night-hdpi")
        )
}
