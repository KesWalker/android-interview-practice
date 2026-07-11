package mvvm

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.concurrent.CountDownLatch

// Task 3: the ViewModel is the only thing allowed to mutate the state it exposes.
class T3FavoritesViewModelTest {
    @Test fun `starts with nothing favorited`() {
        val viewModel = FavoritesViewModel()
        assertEquals(emptySet<String>(), viewModel.state.value.favorites)
    }

    @Test fun `toggling once favorites the id, toggling again unfavorites it`() {
        val viewModel = FavoritesViewModel()
        viewModel.toggleFavorite("a")
        assertEquals(setOf("a"), viewModel.state.value.favorites)
        viewModel.toggleFavorite("a")
        assertEquals(emptySet<String>(), viewModel.state.value.favorites)
    }

    @Test fun `every id toggled by many threads at once ends up favorited exactly once`() {
        val viewModel = FavoritesViewModel()
        val threadCount = 8
        val idsPerThread = 250
        val ready = CountDownLatch(threadCount)
        val start = CountDownLatch(1)

        val threads = (0 until threadCount).map { threadIndex ->
            Thread {
                ready.countDown()
                start.await()
                repeat(idsPerThread) { i ->
                    viewModel.toggleFavorite("item-${threadIndex * idsPerThread + i}")
                }
            }
        }

        threads.forEach { it.start() }
        ready.await()
        start.countDown()
        threads.forEach { it.join() }

        assertEquals(threadCount * idsPerThread, viewModel.state.value.favorites.size)
    }
}
