package hoisting

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 6: the hoisting rule, owner must reach every reader AND every writer.
//
//   App
//    L Screen
//        L Content
//            L SearchBar
//            L ResultsList
class T6ResolveOwnerTest {
    private val app = Node("App", null)
    private val screen = Node("Screen", app)
    private val content = Node("Content", screen)
    private val searchBar = Node("SearchBar", content)
    private val resultsList = Node("ResultsList", content)

    @Test fun `owner is the reader itself when it is also the only writer`() {
        assertEquals(
            searchBar,
            resolveStateOwner(readers = listOf(searchBar), writers = listOf(searchBar))
        )
    }

    @Test fun `owner already covers the writer when readers include multiple siblings`() {
        assertEquals(
            content,
            resolveStateOwner(readers = listOf(searchBar, resultsList), writers = listOf(searchBar))
        )
    }

    @Test fun `a writer outside the readers' natural ancestor forces a further climb`() {
        // ResultsList is the only reader, so LCA(readers) alone is ResultsList
        // itself, but SearchBar (the writer) is not a descendant of it, so the
        // owner must climb up to their shared ancestor instead.
        val owner = resolveStateOwner(readers = listOf(resultsList), writers = listOf(searchBar))
        assertEquals(content, owner)
    }
}
