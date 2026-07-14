package viewmodelreal

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class T4GreeterViewModelFactoryTest {
    private val dispatcher = StandardTestDispatcher()

    @Before fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test fun `the factory injects its constructor dependency into the ViewModel`() {
        val factory = GreeterViewModelFactory("Hello, Kes")
        val provider = ViewModelProvider(ViewModelStore(), factory)

        val vm = provider[GreeterViewModel::class.java]

        assertEquals("Hello, Kes", vm.greeting)
    }

    @Test fun `the same provider returns the cached instance instead of building a new one`() {
        val factory = GreeterViewModelFactory("Hi there")
        val provider = ViewModelProvider(ViewModelStore(), factory)

        val first = provider[GreeterViewModel::class.java]
        val second = provider[GreeterViewModel::class.java]

        assertSame(first, second)
    }

    @Test fun `two different factories build ViewModels with their own greeting`() {
        val storeA = ViewModelStore()
        val storeB = ViewModelStore()
        val vmA = ViewModelProvider(storeA, GreeterViewModelFactory("A"))[GreeterViewModel::class.java]
        val vmB = ViewModelProvider(storeB, GreeterViewModelFactory("B"))[GreeterViewModel::class.java]

        assertEquals("A", vmA.greeting)
        assertEquals("B", vmB.greeting)
    }
}
