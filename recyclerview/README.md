# RecyclerView

Practice module for the **RecyclerView + XML Views** topic on Android
Interview Prep.

This one uses the real `androidx.recyclerview` APIs against a real XML item
layout (`res/layout/item_person.xml`), not a hand-rolled stand-in. You'll
wire up an adapter and ViewHolder, write a `DiffUtil.ItemCallback`, drive a
partial rebind off a change payload, use stable ids to keep a row's identity
across a move, pick a LayoutManager, and prove that RecyclerView actually
reuses ViewHolders instead of recreating them while scrolling. Each task is
a small piece that's currently broken or unwritten, with a matching test
that's **red**. Make each test **green**, one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :recyclerview:test                         # run everything
./gradlew :recyclerview:test --tests "*T1PersonAdapterTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/recyclerview/Tasks.kt`.

1. **`PersonAdapter`** (`T1PersonAdapterTest`) - inflate `item_person.xml` into a `PersonViewHolder` in `onCreateViewHolder`, then bind a row's name and age in `onBindViewHolder`.
2. **`PersonDiffCallback.areItemsTheSame` / `areContentsTheSame`** (`T2DiffCallbackTest`) - tell `ListAdapter` when two rows are the same real-world entity versus when their visible content actually changed, so a reorder fires a move and an edit fires a change.
3. **`PersonDiffCallback.getChangePayload` + `PartialRebindAdapter.onBindViewHolder(holder, position, payloads)`** (`T3PartialRebindTest`) - describe exactly what changed and use that payload to patch just the affected view, instead of paying for a full rebind.
4. **`PersonListAdapter.getItemId`** (`T4StableIdsTest`) - with `setHasStableIds(true)` already on, return a real persistent identity so RecyclerView can track a moved row's ViewHolder instead of losing it.
5. **`createLayoutManager`** (`T5LayoutManagerTest`) - choose a `GridLayoutManager` or a `LinearLayoutManager` based on span count; the LayoutManager, not the adapter, decides where each item lands.
6. **`RecyclableAdapter.onBindViewHolder`** (`T6RecyclingTest`) - bind every field on every call, including resetting `badgeText`'s visibility, so a recycled ViewHolder never keeps a stale badge from the row it used to show.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
