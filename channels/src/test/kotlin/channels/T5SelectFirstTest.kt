package channels

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class T5SelectFirstTest {
    @Test fun `returns value from faster channel`() = runTest {
        val fast = Channel<String>(1)
        val slow = Channel<String>(1)
        launch { fast.send("fast") }
        launch { delay(100); slow.send("slow") }
        assertEquals("fast", selectFirst(fast, slow))
    }

    @Test fun `returns from second if first is slower`() = runTest {
        val a = Channel<Int>(1)
        val b = Channel<Int>(1)
        launch { delay(100); a.send(1) }
        launch { b.send(2) }
        assertEquals(2, selectFirst(a, b))
    }
}
