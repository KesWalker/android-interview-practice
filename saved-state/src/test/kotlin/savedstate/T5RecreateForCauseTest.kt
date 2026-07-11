package savedstate

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 5: extends recreate's two-way split to a third cause, task removal.
class T5RecreateForCauseTest {
    private val original = UiState(
        viewModelField = "vm",
        savedStateField = "saved",
        diskField = "disk"
    )

    @Test fun `config change keeps everything`() {
        assertEquals(original, recreateForCause(original, RecreationCause.CONFIG_CHANGE))
    }

    @Test fun `process death clears only the view model field`() {
        assertEquals(
            UiState(viewModelField = null, savedStateField = "saved", diskField = "disk"),
            recreateForCause(original, RecreationCause.PROCESS_DEATH)
        )
    }

    @Test fun `task removal clears both the view model and saved state fields`() {
        assertEquals(
            UiState(viewModelField = null, savedStateField = null, diskField = "disk"),
            recreateForCause(original, RecreationCause.TASK_REMOVED)
        )
    }
}
