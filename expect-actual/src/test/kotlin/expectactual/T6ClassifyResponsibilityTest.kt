package expectactual

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 6: the shared-logic/platform-UI boundary call an interviewer will probe.
class T6ClassifyResponsibilityTest {
    @Test fun `pure logic belongs in commonMain`() {
        assertEquals(SourceSet.COMMON_MAIN, classifyResponsibility(Responsibility.VALIDATE_EMAIL_FORMAT))
        assertEquals(SourceSet.COMMON_MAIN, classifyResponsibility(Responsibility.COMPUTE_ORDER_TOTAL))
        assertEquals(SourceSet.COMMON_MAIN, classifyResponsibility(Responsibility.PARSE_JSON_RESPONSE))
        assertEquals(SourceSet.COMMON_MAIN, classifyResponsibility(Responsibility.RETRY_WITH_BACKOFF))
    }

    @Test fun `anything touching a platform API belongs in a platform source set`() {
        assertEquals(SourceSet.PLATFORM_MAIN, classifyResponsibility(Responsibility.RENDER_NATIVE_BUTTON))
        assertEquals(SourceSet.PLATFORM_MAIN, classifyResponsibility(Responsibility.ACCESS_CAMERA_HARDWARE))
        assertEquals(SourceSet.PLATFORM_MAIN, classifyResponsibility(Responsibility.SHOW_PLATFORM_TOAST))
        assertEquals(SourceSet.PLATFORM_MAIN, classifyResponsibility(Responsibility.READ_FILE_FROM_DISK))
    }
}
