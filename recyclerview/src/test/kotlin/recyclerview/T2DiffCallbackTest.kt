package recyclerview

import android.os.Looper
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class T2DiffCallbackTest {

    @Test
    fun `a reorder with unchanged content fires a move, not a change`() {
        val adapter = PersonListAdapter()
        val original = listOf(
            Person(1, "Ada", 30),
            Person(2, "Grace", 40),
            Person(3, "Rin", 25)
        )
        adapter.submitList(original)
        shadowOf(Looper.getMainLooper()).idle()

        // Grace (id 2) hops to the front; Ada and Rin keep their relative order
        // and their content, so this should be a pure move.
        val reordered = listOf(original[1], original[0], original[2])
        adapter.submitList(reordered)
        shadowOf(Looper.getMainLooper()).idle()

        assertEquals(1, adapter.moveCount)
        assertEquals(0, adapter.changeCount)
    }

    @Test
    fun `an edited field at the same position fires a change, not a move`() {
        val adapter = PersonListAdapter()
        val original = listOf(
            Person(1, "Ada", 30),
            Person(2, "Grace", 40)
        )
        adapter.submitList(original)
        shadowOf(Looper.getMainLooper()).idle()

        val edited = listOf(original[0].copy(age = 31), original[1])
        adapter.submitList(edited)
        shadowOf(Looper.getMainLooper()).idle()

        assertEquals(0, adapter.moveCount)
        assertEquals(1, adapter.changeCount)
    }
}
