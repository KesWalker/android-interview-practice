package channels

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class T2PipelineTest {
    @Test fun `doubles each value`() = runTest {
        val source = Channel<Int>(3)
        launch { for (i in listOf(1, 2, 3)) source.send(i); source.close() }
        val result = doubled(source)
        assertEquals(listOf(2, 4, 6), buildList { for (v in result) add(v) })
    }

    @Test fun `empty source produces empty output`() = runTest {
        val source = Channel<Int>()
        launch { source.close() }
        val result = doubled(source)
        assertEquals(emptyList<Int>(), buildList { for (v in result) add(v) })
    }
}
