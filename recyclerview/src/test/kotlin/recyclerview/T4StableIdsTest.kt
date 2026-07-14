package recyclerview

import android.os.Looper
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertSame
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class T4StableIdsTest {

    @Test
    fun `a moved row keeps the same ViewHolder because its id followed it`() {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        val adapter = PersonListAdapter()
        val recyclerView = RecyclerView(context).apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }

        val initial = listOf(
            Person(1, "Ada", 30),
            Person(2, "Grace", 40),
            Person(3, "Rin", 25)
        )
        adapter.submitList(initial)
        shadowOf(Looper.getMainLooper()).idle()
        recyclerView.measure(
            View.MeasureSpec.makeMeasureSpec(300, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(1000, View.MeasureSpec.EXACTLY)
        )
        recyclerView.layout(0, 0, 300, 1000)

        val graceBefore = recyclerView.findViewHolderForItemId(2)
        assertNotNull(graceBefore)
        assertEquals("Grace", (graceBefore as PersonViewHolder).nameText.text.toString())

        // Grace (id 2) moves from index 1 to the front.
        adapter.submitList(listOf(initial[1], initial[0], initial[2]))
        shadowOf(Looper.getMainLooper()).idle()
        recyclerView.measure(
            View.MeasureSpec.makeMeasureSpec(300, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(1000, View.MeasureSpec.EXACTLY)
        )
        recyclerView.layout(0, 0, 300, 1000)

        val graceAfter = recyclerView.findViewHolderForItemId(2)
        assertNotNull(graceAfter)
        assertSame(graceBefore, graceAfter)
        assertEquals(0, graceAfter!!.adapterPosition)
    }
}
