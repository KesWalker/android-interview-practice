package savedstate

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 4: only process death clears the ViewModel-held field.
class RecreateTest {
    private val original = UiState(
        viewModelField = "vm",
        savedStateField = "saved",
        diskField = "disk"
    )

    @Test fun `config change keeps everything`() {
        assertEquals(original, recreate(original, killedProcess = false))
    }

    @Test fun `process death clears only the view model field`() {
        assertEquals(
            UiState(viewModelField = null, savedStateField = "saved", diskField = "disk"),
            recreate(original, killedProcess = true)
        )
    }
}
