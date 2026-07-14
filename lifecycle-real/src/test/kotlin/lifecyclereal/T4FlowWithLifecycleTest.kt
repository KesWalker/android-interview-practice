package lifecyclereal

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.testing.TestLifecycleOwner
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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
class T4FlowWithLifecycleTest {
    private val dispatcher = StandardTestDispatcher()

    @Before fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After fun tearDown() {
        Dispatchers.resetMain()
    }

    /** Ticks once a second, counting every value it actually produces. */
    private class CountingTicker {
        var produced = 0
            private set

        fun flow(): Flow<Int> = flow {
            while (true) {
                delay(1_000)
                produced++
                emit(produced)
            }
        }
    }

    @Test fun `flowWithLifecycle stops and restarts collection just like repeatOnLifecycle`() =
        runTest(dispatcher) {
            val owner = TestLifecycleOwner(
                initialState = Lifecycle.State.INITIALIZED,
                coroutineDispatcher = Dispatchers.Unconfined
            )
            val ticker = CountingTicker()
            val collected = mutableListOf<Int>()

            collectWithFlowWithLifecycle(owner, ticker.flow(), backgroundScope, collected)

            owner.currentState = Lifecycle.State.STARTED
            advanceTimeBy(2_500)
            runCurrent()
            assertEquals(listOf(1, 2), collected)
            assertEquals(2, ticker.produced)

            owner.currentState = Lifecycle.State.CREATED
            advanceTimeBy(4_000)
            runCurrent()
            assertEquals(2, ticker.produced)

            owner.currentState = Lifecycle.State.STARTED
            advanceTimeBy(1_500)
            runCurrent()
            assertEquals(3, ticker.produced)
            assertEquals(listOf(1, 2, 3), collected)

            owner.currentState = Lifecycle.State.DESTROYED
        }
}
