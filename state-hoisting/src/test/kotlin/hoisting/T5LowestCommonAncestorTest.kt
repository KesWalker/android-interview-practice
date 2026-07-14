package hoisting

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 5: lowest common ancestor over a node's parent chain.
//
//   App
//    L Screen
//        L Toolbar
//        |   L SearchIcon
//        L Content
//            L SearchBar
//            L ResultsList
//                L ResultItem1
//                L ResultItem2
class T5LowestCommonAncestorTest {
    private val app = Node("App", null)
    private val screen = Node("Screen", app)
    private val toolbar = Node("Toolbar", screen)
    private val searchIcon = Node("SearchIcon", toolbar)
    private val content = Node("Content", screen)
    private val searchBar = Node("SearchBar", content)
    private val resultsList = Node("ResultsList", content)
    private val resultItem1 = Node("ResultItem1", resultsList)
    private val resultItem2 = Node("ResultItem2", resultsList)

    @Test fun `siblings share their direct parent`() {
        assertEquals(resultsList, lowestCommonAncestor(listOf(resultItem1, resultItem2)))
    }

    @Test fun `cousins share a higher ancestor`() {
        assertEquals(content, lowestCommonAncestor(listOf(searchBar, resultItem1)))
    }

    @Test fun `distant nodes climb all the way to their shared ancestor`() {
        assertEquals(screen, lowestCommonAncestor(listOf(searchIcon, resultItem1)))
    }

    @Test fun `a single node is its own lowest common ancestor`() {
        assertEquals(resultItem1, lowestCommonAncestor(listOf(resultItem1)))
    }
}
