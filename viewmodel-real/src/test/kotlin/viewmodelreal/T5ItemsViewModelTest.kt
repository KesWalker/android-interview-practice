package viewmodelreal

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class T5ItemsViewModelTest {
    private val dispatcher = StandardTestDispatcher()

    @Before fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After fun tearDown() {
        Dispatchers.resetMain()
    }

    private class FakeItemsRepository : ItemsRepository {
        var collectCount = 0
            private set

        override val items: Flow<List<String>> = flow {
            collectCount++
            emit(listOf("a", "b"))
            awaitCancellation()
        }
    }

    @Test fun `the repository is not touched until the first collector subscribes`() = runTest(dispatcher) {
        val repo = FakeItemsRepository()
        ItemsViewModel(repo)

        assertEquals(0, repo.collectCount)
    }

    @Test fun `a brief gap between collectors reuses the same upstream subscription`() = runTest(dispatcher) {
        val repo = FakeItemsRepository()
        val vm = ItemsViewModel(repo)

        val job1 = launch { vm.items.collect {} }
        runCurrent()
        assertEquals(1, repo.collectCount)
        assertEquals(listOf("a", "b"), vm.items.value)

        job1.cancel()
        advanceTimeBy(4_000)
        runCurrent()

        val job2 = launch { vm.items.collect {} }
        runCurrent()
        assertEquals(1, repo.collectCount)

        job2.cancel()
        advanceTimeBy(6_000)
        runCurrent()
    }

    @Test fun `once the 5 second grace window fully elapses, the next collector triggers a fresh fetch`() =
        runTest(dispatcher) {
            val repo = FakeItemsRepository()
            val vm = ItemsViewModel(repo)

            val job1 = launch { vm.items.collect {} }
            runCurrent()
            job1.cancel()

            advanceTimeBy(5_001)
            runCurrent()

            val job2 = launch { vm.items.collect {} }
            runCurrent()
            assertEquals(2, repo.collectCount)

            job2.cancel()
        }
}
