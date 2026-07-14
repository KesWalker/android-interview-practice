package recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.util.concurrent.Executor

/**
 * RecyclerView + XML practice.
 *
 * Each task below is broken or unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to
 * implement it so its test goes GREEN. Run a single test class from the
 * gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :recyclerview:test
 */

data class Person(
    val id: Long,
    val name: String,
    val age: Int,
    val isNew: Boolean = false
)

/** Runs a background diff computation synchronously, so tests don't need real threads. */
internal val syncExecutor = Executor { it.run() }

/** Shared row holder for res/layout/item_person.xml, used by every task below. */
class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val nameText: TextView = itemView.findViewById(R.id.nameText)
    val ageText: TextView = itemView.findViewById(R.id.ageText)
    val badgeText: TextView = itemView.findViewById(R.id.badgeText)
}

// ---------------------------------------------------------------------
// T1: a real RecyclerView.Adapter + ViewHolder inflating an XML layout
// ---------------------------------------------------------------------

// TODO(t1): T1PersonAdapterTest
// Wire up the two halves of a working adapter: inflate item_person.xml into
// a PersonViewHolder in onCreateViewHolder, then push this position's data
// into the inflated views in onBindViewHolder.
class PersonAdapter(private val items: List<Person>) : RecyclerView.Adapter<PersonViewHolder>() {

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        TODO("t1: inflate R.layout.item_person with LayoutInflater and wrap it in a PersonViewHolder")
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        TODO("t1: set holder.nameText.text and holder.ageText.text from items[position]")
    }
}

// ---------------------------------------------------------------------
// T2 + T3: a real DiffUtil.ItemCallback, including a change payload
// ---------------------------------------------------------------------

object PersonDiffCallback : DiffUtil.ItemCallback<Person>() {

    // TODO(t2): T2DiffCallbackTest
    // Two Persons represent the "same" row if they refer to the same
    // real-world entity, even if their fields differ. Compare by id, not by
    // full equality.
    override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
        TODO("t2: return oldItem.id == newItem.id")
    }

    // TODO(t2): T2DiffCallbackTest
    // Given two Persons that ARE the same row (areItemsTheSame already
    // true), decide whether anything a user would notice actually changed.
    override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
        TODO("t2: return oldItem == newItem")
    }

    // TODO(t3): T3PartialRebindTest
    // Called only when areItemsTheSame is true and areContentsTheSame is
    // false. Return a small payload describing WHAT changed (here, whether
    // the age changed) so the adapter can patch just that piece of the row
    // instead of rebinding the whole thing. Returning null falls back to a
    // full rebind, so only do that if nothing we care about changed.
    override fun getChangePayload(oldItem: Person, newItem: Person): Any? {
        TODO("t3: if oldItem.age != newItem.age return a payload marker such as \"age\", else null")
    }
}

// ---------------------------------------------------------------------
// T4: stable ids
// ---------------------------------------------------------------------

// TODO(t4): T4StableIdsTest
// setHasStableIds(true) tells RecyclerView it can trust getItemId() to name
// a row across list changes, so it can match old and new ViewHolders by
// identity (a move) instead of tearing one down and building another
// (a remove + insert). Returning something that ISN'T a stable identity,
// like the row's position, would defeat this even with the flag on: two
// different Persons that happen to share a position would look like the
// "same" row to RecyclerView.
class PersonListAdapter : ListAdapter<Person, PersonViewHolder>(
    AsyncDifferConfig.Builder(PersonDiffCallback)
        .setBackgroundThreadExecutor(syncExecutor)
        .build()
) {
    var moveCount = 0
        private set
    var changeCount = 0
        private set

    init {
        setHasStableIds(true)
        registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
                moveCount++
            }

            override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
                changeCount++
            }
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_person, parent, false)
        return PersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = getItem(position)
        holder.nameText.text = person.name
        holder.ageText.text = person.age.toString()
    }

    override fun getItemId(position: Int): Long {
        TODO("t4: return getItem(position).id, a value that follows the row across list changes")
    }
}

// ---------------------------------------------------------------------
// T3 (adapter half): partial rebind driven by getChangePayload
// ---------------------------------------------------------------------

/** A separate ListAdapter so its partial-rebind override doesn't gate T4's initial layout. */
class PartialRebindAdapter : ListAdapter<Person, PersonViewHolder>(
    AsyncDifferConfig.Builder(PersonDiffCallback)
        .setBackgroundThreadExecutor(syncExecutor)
        .build()
) {
    var fullBindCount = 0
        private set
    var partialBindCount = 0
        private set

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_person, parent, false)
        return PersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        fullBindCount++
        val person = getItem(position)
        holder.nameText.text = person.name
        holder.ageText.text = person.age.toString()
    }

    // TODO(t3): T3PartialRebindTest
    // RecyclerView always calls this 3-arg overload; the default
    // implementation just forwards to the 2-arg onBindViewHolder above
    // (a full rebind). When payloads is non-empty, patch only what the
    // payload says changed (holder.ageText) instead of doing that full
    // rebind, so unrelated views (like nameText) aren't touched.
    override fun onBindViewHolder(holder: PersonViewHolder, position: Int, payloads: MutableList<Any>) {
        TODO("t3: if payloads is non-empty, update holder.ageText from getItem(position) and return; otherwise call onBindViewHolder(holder, position)")
    }
}

// ---------------------------------------------------------------------
// T5: LayoutManager choice
// ---------------------------------------------------------------------

// TODO(t5): T5LayoutManagerTest
// Return a GridLayoutManager with the given span count when spanCount > 1,
// otherwise a plain vertical LinearLayoutManager. The LayoutManager is what
// actually decides where each item view lands on screen; the adapter never
// positions anything itself.
fun createLayoutManager(context: Context, spanCount: Int): RecyclerView.LayoutManager {
    TODO("t5: spanCount > 1 -> GridLayoutManager(context, spanCount), else LinearLayoutManager(context)")
}

// ---------------------------------------------------------------------
// T6: view recycling
// ---------------------------------------------------------------------

/** Counts ViewHolder creation so tests can prove recycling, not just rendering. */
class RecyclableAdapter(private val items: List<Person>) : RecyclerView.Adapter<PersonViewHolder>() {

    var createCount = 0
        private set

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        createCount++
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_person, parent, false)
        return PersonViewHolder(view)
    }

    // TODO(t6): T6RecyclingTest
    // A recycled PersonViewHolder still has whatever badgeText state the
    // PREVIOUS row left it in. Bind name and age, and set badgeText's
    // visibility (View.VISIBLE / View.INVISIBLE) from items[position].isNew
    // on every call, in both directions, so a recycled view never keeps a
    // stale badge from the row it used to show.
    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        TODO("t6: bind nameText/ageText, then set badgeText visibility from items[position].isNew both ways")
    }
}
