package resources

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 5
class IsValidQualifierOrderTest {
    @Test fun `a bare base name is trivially valid`() =
        assertTrue(isValidQualifierOrder("values"))

    @Test fun `a single qualifier is trivially valid`() =
        assertTrue(isValidQualifierOrder("values-en"))

    @Test fun `locale then night then density is valid`() =
        assertTrue(isValidQualifierOrder("drawable-en-night-hdpi"))

    @Test fun `locale after night is invalid`() =
        assertFalse(isValidQualifierOrder("drawable-night-hdpi-en"))

    @Test fun `density before night is invalid`() =
        assertFalse(isValidQualifierOrder("drawable-hdpi-night"))
}
