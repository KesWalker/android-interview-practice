# PagingSource

Practice module for the **Paging 3** topic on Android Interview Prep.

You're building the key math and load handling behind a `PagingSource`:
a config that sizes the first load bigger than the rest, the prevKey/nextKey
arithmetic that tells the pager when it's run out of data in either
direction, a `load()` that handles refresh, append and prepend (and turns a
backend failure into an `Error` instead of crashing), the prefetchDistance
rule that decides when scrolling near the edge should trigger the next
append, and `getRefreshKey`, the trickiest one: after the list is torn down
and rebuilt, which loaded page do you rebuild around? Each task is a small
function that's currently broken or unwritten, with a matching test that's
**red**. Make each test **green**, one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :paging-source:test                       # run everything
./gradlew :paging-source:test --tests "*T1DefaultConfigTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/paging/Tasks.kt`.

1. **`defaultConfig`** (`T1DefaultConfigTest`) - size the first load to three pages, and default prefetchDistance to one page.
2. **`deriveKeys`** (`T2DeriveKeysTest`) - work out a page's prevKey/nextKey, null in whichever direction there's no more data.
3. **`load`** (`T3LoadTest`) - fetch a page for refresh, append or prepend, and turn a backend failure into `LoadResult.Error` instead of throwing.
4. **`shouldPrefetchNextPage`** (`T4PrefetchDistanceTest`) - decide when scrolling near the edge of what's loaded should trigger the next append.
5. **`getRefreshKey`** (`T5GetRefreshKeyTest`) - after invalidation, find the loaded page closest to the anchor position and refresh around it.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
