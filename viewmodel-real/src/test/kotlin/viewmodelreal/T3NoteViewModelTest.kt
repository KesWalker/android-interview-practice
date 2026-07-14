package viewmodelreal

import androidx.lifecycle.SavedStateHandle
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
class T3NoteViewModelTest {
    private val dispatcher = StandardTestDispatcher()

    @Before fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test fun `noteState starts empty and reflects updateNote immediately`() {
        val vm = NoteViewModel(SavedStateHandle())
        assertEquals("", vm.noteState.value)

        vm.updateNote("Buy milk")
        assertEquals("Buy milk", vm.noteState.value)
    }

    @Test fun `a ViewModel rebuilt with the same handle sees the restored note`() {
        val handle = SavedStateHandle()
        val original = NoteViewModel(handle)
        original.updateNote("Buy milk")

        // Simulated process death: the OS kept the SavedStateHandle's Bundle around and hands
        // the same handle to a freshly-constructed ViewModel.
        val restored = NoteViewModel(handle)

        assertEquals("Buy milk", restored.noteState.value)
    }

    @Test fun `getStateFlow keeps emitting after later writes, even for a flow obtained earlier`() {
        val handle = SavedStateHandle()
        val vm = NoteViewModel(handle)
        val flow = vm.noteState

        vm.updateNote("first draft")
        assertEquals("first draft", flow.value)

        vm.updateNote("final draft")
        assertEquals("final draft", flow.value)
    }
}
