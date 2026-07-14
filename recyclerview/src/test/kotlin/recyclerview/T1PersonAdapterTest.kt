package recyclerview

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class T1PersonAdapterTest {

    @Test
    fun `binds each row's name and age into the inflated layout`() {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        val items = listOf(
            Person(id = 1, name = "Ada", age = 30),
            Person(id = 2, name = "Grace", age = 40),
            Person(id = 3, name = "Rin", age = 25)
        )

        val recyclerView = RecyclerView(context).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = PersonAdapter(items)
        }
        recyclerView.measure(
            View.MeasureSpec.makeMeasureSpec(300, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(1000, View.MeasureSpec.EXACTLY)
        )
        recyclerView.layout(0, 0, 300, 1000)

        assertEquals(3, recyclerView.childCount)
        for (i in items.indices) {
            val holder = recyclerView.findViewHolderForAdapterPosition(i) as PersonViewHolder
            assertEquals(items[i].name, holder.nameText.text.toString())
            assertEquals(items[i].age.toString(), holder.ageText.text.toString())
        }
    }
}
