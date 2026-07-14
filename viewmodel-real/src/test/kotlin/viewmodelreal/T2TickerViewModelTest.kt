package viewmodelreal

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import kotlinx.coroutines.Dispatchers
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
class T2TickerViewModelTest {
    private val dispatcher = StandardTestDispatcher()

    @Before fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test fun `ticking runs on viewModelScope while the ViewModel is alive`() = runTest(dispatcher) {
        val vm = TickerViewModel()

        vm.startTicking()
        advanceTimeBy(3_500)
        runCurrent()

        assertEquals(3, vm.tickCount.value)
    }

    @Test fun `ticking stops for good once the ViewModel is cleared`() = runTest(dispatcher) {
        val store = ViewModelStore()
        val vm = ViewModelProvider(store, ViewModelProvider.NewInstanceFactory())[TickerViewModel::class.java]

        vm.startTicking()
        advanceTimeBy(3_500)
        runCurrent()
        val ticksBeforeClear = vm.tickCount.value
        assertEquals(3, ticksBeforeClear)

        // This is what Android does when the screen that owns this ViewModel is finished for
        // good: it clears the ViewModelStore, which cancels every ViewModel's viewModelScope
        // right before onCleared runs.
        store.clear()

        advanceTimeBy(10_000)
        runCurrent()

        assertEquals(ticksBeforeClear, vm.tickCount.value)
    }
}
