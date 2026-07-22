package channels

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class T6ChannelToFlowTest {
    @Test fun `converts channel elements to flow`() = runTest {
        val ch = Channel<Int>(3)
        launch { for (i in listOf(1, 2, 3)) ch.send(i); ch.close() }
        val result = channelToFlow(ch).toList()
        assertEquals(listOf(1, 2, 3), result)
    }

    @Test fun `empty channel produces empty flow`() = runTest {
        val ch = Channel<String>()
        launch { ch.close() }
        val result = channelToFlow(ch).toList()
        assertEquals(emptyList<String>(), result)
    }
}
