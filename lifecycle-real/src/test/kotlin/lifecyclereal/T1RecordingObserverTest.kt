package lifecyclereal

import androidx.lifecycle.Lifecycle
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
class T1RecordingObserverTest {
    private val dispatcher = StandardTestDispatcher()

    @Before fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test fun `callbacks fire in order as the owner moves through its states`() {
        val owner = TestLifecycleOwner(
            initialState = Lifecycle.State.INITIALIZED,
            coroutineDispatcher = Dispatchers.Unconfined
        )
        val events = mutableListOf<String>()
        owner.lifecycle.addObserver(RecordingObserver(events))

        owner.currentState = Lifecycle.State.CREATED
        owner.currentState = Lifecycle.State.STARTED
        owner.currentState = Lifecycle.State.RESUMED
        owner.currentState = Lifecycle.State.STARTED
        owner.currentState = Lifecycle.State.CREATED
        owner.currentState = Lifecycle.State.DESTROYED

        assertEquals(
            listOf("CREATE", "START", "RESUME", "PAUSE", "STOP", "DESTROY"),
            events
        )
    }

    @Test fun `an observer added once the owner is already STARTED gets the catch-up callbacks`() {
        val owner = TestLifecycleOwner(
            initialState = Lifecycle.State.STARTED,
            coroutineDispatcher = Dispatchers.Unconfined
        )
        val events = mutableListOf<String>()
        owner.lifecycle.addObserver(RecordingObserver(events))

        assertEquals(listOf("CREATE", "START"), events)
    }
}
