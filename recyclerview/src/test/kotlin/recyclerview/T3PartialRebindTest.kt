package recyclerview

import android.os.Looper
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class T3PartialRebindTest {

    @Test
    fun `an age-only change patches ageText without a full rebind`() {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        val adapter = PartialRebindAdapter()
        val recyclerView = RecyclerView(context).apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }

        adapter.submitList(listOf(Person(1, "Ada", 30), Person(2, "Grace", 40)))
        shadowOf(Looper.getMainLooper()).idle()
        recyclerView.measure(
            View.MeasureSpec.makeMeasureSpec(300, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(1000, View.MeasureSpec.EXACTLY)
        )
        recyclerView.layout(0, 0, 300, 1000)

        val fullBindsAfterInitialLayout = adapter.fullBindCount
        assertEquals(0, adapter.partialBindCount)

        adapter.submitList(listOf(Person(1, "Ada", 31), Person(2, "Grace", 40)))
        shadowOf(Looper.getMainLooper()).idle()
        recyclerView.measure(
            View.MeasureSpec.makeMeasureSpec(300, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(1000, View.MeasureSpec.EXACTLY)
        )
        recyclerView.layout(0, 0, 300, 1000)

        val holder = recyclerView.findViewHolderForAdapterPosition(0) as PersonViewHolder
        assertEquals("31", holder.ageText.text.toString())
        assertEquals("Ada", holder.nameText.text.toString())

        assertEquals(1, adapter.partialBindCount)
        assertEquals(fullBindsAfterInitialLayout, adapter.fullBindCount)
    }
}
