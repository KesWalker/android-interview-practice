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
class T3PlainLifecycleScopeTest {
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

    @Test fun `a plain lifecycleScope collector keeps running below STARTED, wasting work`() =
        runTest(dispatcher) {
            val owner = TestLifecycleOwner(
                initialState = Lifecycle.State.STARTED,
                coroutineDispatcher = Dispatchers.Unconfined
            )
            val ticker = CountingTicker()
            val collected = mutableListOf<Int>()

            collectAlways(owner, ticker.flow(), collected)

            advanceTimeBy(2_000)
            runCurrent()
            assertEquals(listOf(1, 2), collected)

            owner.currentState = Lifecycle.State.CREATED
            advanceTimeBy(2_000)
            runCurrent()
            // Stopped, but the plain lifecycleScope collector doesn't know or care.
            assertEquals(listOf(1, 2, 3, 4), collected)

            owner.currentState = Lifecycle.State.DESTROYED
            advanceTimeBy(2_000)
            runCurrent()
            // Only DESTROY actually cancels lifecycleScope.
            assertEquals(listOf(1, 2, 3, 4), collected)
        }
}
