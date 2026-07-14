package recyclerview

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
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
class T5LayoutManagerTest {

    private val context get() = ApplicationProvider.getApplicationContext<android.content.Context>()

    private fun dp(value: Int): Int =
        (value * context.resources.displayMetrics.density).toInt()

    private val items = listOf(
        Person(1, "Ada", 30),
        Person(2, "Grace", 40),
        Person(3, "Rin", 25),
        Person(4, "Kay", 22)
    )

    @Test
    fun `spanCount above 1 arranges items into a grid`() {
        val layoutManager = createLayoutManager(context, spanCount = 2)
        assertTrue(layoutManager is GridLayoutManager)

        val recyclerView = RecyclerView(context).apply {
            this.layoutManager = layoutManager
            adapter = PersonAdapter(items)
        }
        val width = dp(240)
        val height = dp(300)
        recyclerView.measure(
            View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY)
        )
        recyclerView.layout(0, 0, width, height)

        val child0 = recyclerView.getChildAt(0)
        val child1 = recyclerView.getChildAt(1)
        val child2 = recyclerView.getChildAt(2)

        // Two columns: child1 sits to the right of child0 on the same row.
        assertEquals(child0.top, child1.top)
        assertTrue(child1.left > child0.left)
        // child2 wraps to a new row, back at the left edge.
        assertEquals(child0.left, child2.left)
        assertTrue(child2.top > child0.top)
    }

    @Test
    fun `spanCount of 1 stacks items in a single column`() {
        val layoutManager = createLayoutManager(context, spanCount = 1)
        assertTrue(layoutManager is LinearLayoutManager && layoutManager !is GridLayoutManager)

        val recyclerView = RecyclerView(context).apply {
            this.layoutManager = layoutManager
            adapter = PersonAdapter(items)
        }
        val width = dp(240)
        val height = dp(400)
        recyclerView.measure(
            View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY)
        )
        recyclerView.layout(0, 0, width, height)

        val child0 = recyclerView.getChildAt(0)
        val child1 = recyclerView.getChildAt(1)

        assertEquals(child0.left, child1.left)
        assertTrue(child1.top > child0.top)
    }
}
