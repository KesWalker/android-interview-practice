package securestore

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 1: classify a field kind by sensitivity.
class T1ClassifyTest {
    @Test fun `app preferences are public`() {
        assertEquals(Sensitivity.PUBLIC, classify(FieldKind.THEME_PREFERENCE))
        assertEquals(Sensitivity.PUBLIC, classify(FieldKind.LANGUAGE_SETTING))
    }

    @Test fun `fields that identify the user are personal`() {
        assertEquals(Sensitivity.PERSONAL, classify(FieldKind.EMAIL_ADDRESS))
        assertEquals(Sensitivity.PERSONAL, classify(FieldKind.DISPLAY_NAME))
        assertEquals(Sensitivity.PERSONAL, classify(FieldKind.PHONE_NUMBER))
    }

    @Test fun `credentials and tokens are secret`() {
        assertEquals(Sensitivity.SECRET, classify(FieldKind.AUTH_TOKEN))
        assertEquals(Sensitivity.SECRET, classify(FieldKind.ACCOUNT_PASSWORD))
        assertEquals(Sensitivity.SECRET, classify(FieldKind.API_KEY))
    }
}
