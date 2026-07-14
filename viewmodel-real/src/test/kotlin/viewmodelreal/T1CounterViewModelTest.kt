package viewmodelreal

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
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
class T1CounterViewModelTest {
    private val dispatcher = StandardTestDispatcher()

    @Before fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test fun `count starts at zero and goes up one increment at a time`() {
        val vm = CounterViewModel()
        assertEquals(0, vm.count.value)

        vm.increment()
        assertEquals(1, vm.count.value)

        vm.increment()
        vm.increment()
        assertEquals(3, vm.count.value)
    }

    @Test fun `reset brings count back to zero`() {
        val vm = CounterViewModel()
        vm.increment()
        vm.increment()
        assertEquals(2, vm.count.value)

        vm.reset()
        assertEquals(0, vm.count.value)
    }
}
