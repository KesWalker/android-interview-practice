package recyclerview

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class T6RecyclingTest {

    private val context get() = ApplicationProvider.getApplicationContext<android.content.Context>()

    private fun dp(value: Int): Int =
        (value * context.resources.displayMetrics.density).toInt()

    @Test
    fun `scrolling past the pool reuses ViewHolders and resets the badge`() {
        val itemCount = 20
        val items = (0 until itemCount).map { i ->
            Person(id = i.toLong(), name = "Person $i", age = 20 + i, isNew = i == 0)
        }
        val adapter = RecyclableAdapter(items)
        val recyclerView = RecyclerView(context).apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }

        val width = dp(240)
        val height = dp(230) // shows roughly 3 rows of the fixed 76dp-tall item
        recyclerView.measure(
            View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY)
        )
        recyclerView.layout(0, 0, width, height)

        val firstHolder = recyclerView.findViewHolderForAdapterPosition(0)
        assertEquals(View.VISIBLE, firstHolder!!.itemView.findViewById<View>(R.id.badgeText).visibility)

        // Scroll far past what the recycled view pool would need to hold if every
        // row required its own ViewHolder.
        val rowHeight = dp(76)
        repeat(15) {
            recyclerView.scrollBy(0, rowHeight)
        }

        assertTrue("expected reuse, but createCount was ${adapter.createCount} for $itemCount items",
            adapter.createCount < itemCount)

        for (i in 0 until recyclerView.childCount) {
            val child = recyclerView.getChildAt(i)
            val holder = recyclerView.getChildViewHolder(child) as PersonViewHolder
            val position = recyclerView.getChildAdapterPosition(child)
            val expectedVisibility = if (items[position].isNew) View.VISIBLE else View.INVISIBLE
            assertEquals(
                "row at position $position leaked a stale badge state",
                expectedVisibility,
                holder.badgeText.visibility
            )
        }
    }
}
