package channels

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class T3FanInTest {
    @Test fun `merges two channels`() = runTest {
        val a = Channel<Int>(3)
        val b = Channel<Int>(3)
        launch { for (i in listOf(1, 2, 3)) a.send(i); a.close() }
        launch { for (i in listOf(10, 20, 30)) b.send(i); b.close() }
        val merged = fanIn(listOf(a, b))
        val result = buildList { for (v in merged) add(v) }.sorted()
        assertEquals(listOf(1, 2, 3, 10, 20, 30), result)
    }

    @Test fun `single channel passes through`() = runTest {
        val a = Channel<String>(2)
        launch { a.send("x"); a.send("y"); a.close() }
        val merged = fanIn(listOf(a))
        assertEquals(listOf("x", "y"), buildList { for (v in merged) add(v) })
    }

    @Test fun `empty list produces empty channel`() = runTest {
        val merged = fanIn<Int>(emptyList())
        assertEquals(emptyList<Int>(), buildList { for (v in merged) add(v) })
    }
}
