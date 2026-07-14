# DiffUtil

Practice module for the **RecyclerView (XML)** topic on Android Interview Prep.

You're building `DiffUtil` yourself: given an old list and a new list, plus an
item-identity function and a content-equality function, compute the ordered
list of changes a RecyclerView adapter would apply. That means telling
`areItemsTheSame` apart from `areContentsTheSame`, attaching a change payload
for a partial rebind, spotting a Move instead of a Remove-plus-Insert, and
seeing exactly what breaks when the identity key isn't stable. Each task is a
small function that's currently broken or unwritten, with a matching test
that's **red**. Make each test **green**, one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :diff-util:test                              # run everything
./gradlew :diff-util:test --tests "*T1FindMatchesTest"      # one task
```

## The tasks

All the work is in `src/main/kotlin/diffutil/Tasks.kt`.

1. **`findMatches`** (`T1FindMatchesTest`) - `areItemsTheSame`: match new items to old items by id.
2. **`findRemovals`** (`T2FindRemovalsTest`) - flag old items whose id has no match in the new list.
3. **`findInsertions`** (`T3FindInsertionsTest`) - flag new items whose id has no match in the old list.
4. **`findMoves`** (`T4FindMovesTest`) - spot a matched item whose position broke the increasing order, a Move.
5. **`findChanges`** (`T5FindChangesTest`) - `areContentsTheSame` plus a payload for a partial rebind.
6. **`computeDiff`** (`T6ComputeDiffTest`) - combine all of the above into one ordered diff.
7. **`summarize`** (`T7SummarizeTest`) - tally a diff's op kinds, and see why an unstable key turns a Move into a Remove plus an Insert.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
