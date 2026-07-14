package lifecyclereal

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.testing.TestLifecycleOwner
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
class T5LiveDataObservationTest {
    private val dispatcher = StandardTestDispatcher()

    @Before fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test fun `LiveData is lifecycle-aware with no repeatOnLifecycle wrapping needed`() {
        val owner = TestLifecycleOwner(
            initialState = Lifecycle.State.INITIALIZED,
            coroutineDispatcher = Dispatchers.Unconfined
        )
        val source = MutableLiveData<Int>()
        val observed = mutableListOf<Int>()

        observeWhileActive(owner, source, observed)

        source.value = 1
        assertEquals(emptyList<Int>(), observed)

        owner.currentState = Lifecycle.State.STARTED
        // Becoming active redelivers the latest value that was set while inactive.
        assertEquals(listOf(1), observed)

        source.value = 2
        assertEquals(listOf(1, 2), observed)

        owner.currentState = Lifecycle.State.CREATED
        source.value = 3
        assertEquals(listOf(1, 2), observed)

        owner.currentState = Lifecycle.State.STARTED
        // No repeatOnLifecycle call anywhere, yet the missed value still arrives.
        assertEquals(listOf(1, 2, 3), observed)

        owner.currentState = Lifecycle.State.DESTROYED
        source.value = 4
        assertEquals(listOf(1, 2, 3), observed)
    }
}
