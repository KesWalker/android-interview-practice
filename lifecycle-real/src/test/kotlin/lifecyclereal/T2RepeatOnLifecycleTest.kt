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
class T2RepeatOnLifecycleTest {
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

    @Test fun `collection cancels below STARTED and restarts once STARTED again`() = runTest(dispatcher) {
        val owner = TestLifecycleOwner(
            initialState = Lifecycle.State.INITIALIZED,
            coroutineDispatcher = Dispatchers.Unconfined
        )
        val ticker = CountingTicker()
        val collected = mutableListOf<Int>()

        collectWhileStarted(owner, ticker.flow(), backgroundScope, collected)

        owner.currentState = Lifecycle.State.STARTED
        advanceTimeBy(3_500)
        runCurrent()
        assertEquals(listOf(1, 2, 3), collected)
        assertEquals(3, ticker.produced)

        owner.currentState = Lifecycle.State.CREATED
        advanceTimeBy(5_000)
        runCurrent()
        // The upstream really stopped producing, not just stopped being listened to.
        assertEquals(3, ticker.produced)
        assertEquals(listOf(1, 2, 3), collected)

        owner.currentState = Lifecycle.State.STARTED
        advanceTimeBy(2_000)
        runCurrent()
        assertEquals(5, ticker.produced)
        assertEquals(listOf(1, 2, 3, 4, 5), collected)

        owner.currentState = Lifecycle.State.DESTROYED
    }
}
