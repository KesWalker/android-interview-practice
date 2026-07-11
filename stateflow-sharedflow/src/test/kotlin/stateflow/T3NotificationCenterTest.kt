package stateflow

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 3: SharedFlow replay plus a non-suspending publish.
class T3NotificationCenterTest {
    @Test fun `starts with an empty replay cache`() {
        assertEquals(emptyList<String>(), NotificationCenter().notifications.replayCache)
    }

    @Test fun `publish never fails even under a burst of messages`() {
        val center = NotificationCenter()
        repeat(10) { i -> assertTrue(center.publish("msg$i")) }
    }

    @Test fun `replays only the last two messages to a late subscriber`() {
        val center = NotificationCenter()
        center.publish("a")
        center.publish("b")
        center.publish("c")

        assertEquals(listOf("b", "c"), center.notifications.replayCache)
    }
}
