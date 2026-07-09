package flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 4: switching to the newest query cancels any still-running search.
class SearchResultsTest {
    @Test fun `a single query returns its own result`() = runTest {
        val result = searchResults(flowOf("solo")) { query ->
            delay(5)
            "results:$query"
        }.toList()

        assertEquals(listOf("results:solo"), result)
    }

    @Test fun `a newer query abandons the older search still in flight`() = runTest {
        val queries = flow {
            emit("a")
            delay(10)
            emit("ab")
        }

        val result = searchResults(queries) { query ->
            delay(50)
            "results:$query"
        }.toList()

        assertEquals(listOf("results:ab"), result)
    }

    @Test fun `only the last of several rapid-fire queries survives`() = runTest {
        val queries = flow {
            emit("a")
            emit("b")
            emit("c")
        }

        val result = searchResults(queries) { query ->
            delay(1)
            "results:$query"
        }.toList()

        assertEquals(listOf("results:c"), result)
    }
}
