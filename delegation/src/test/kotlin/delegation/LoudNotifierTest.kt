package delegation

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 1: interface delegation with an override.
class LoudNotifierTest {
    @Test fun `uppercases the message it sends`() {
        val notifier = loudNotifier(SilentNotifier())
        assertEquals("SILENT: HI", notifier.send("hi"))
    }

    @Test fun `still forwards members it doesn't override`() {
        val notifier = loudNotifier(SilentNotifier())
        assertEquals("pong", notifier.ping())
    }

    @Test fun `works with a different message`() {
        val notifier = loudNotifier(SilentNotifier())
        assertEquals("SILENT: BYE", notifier.send("bye"))
    }
}
