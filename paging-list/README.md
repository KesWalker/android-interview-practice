# Paging 3

Practice module for the **Paging 3** topic on Android Interview Prep.

Unlike the earlier `paging-source` module, which modelled the paging contract
in plain Kotlin, this one uses the real `androidx.paging` library: a real
`PagingSource<Int, Movie>`, a real `PagingConfig`, and `paging-testing`'s
`TestPager` to drive it the way a real `Pager` would. Each task is a function
or class that's currently broken or unwritten, with a matching test that's
**red**. Make each test **green**, one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :paging-list:test                       # run everything
./gradlew :paging-list:test --tests "*T1LoadPagesTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/paginglist/Tasks.kt`.

1. **`MoviePagingSource.load`** (`T1LoadPagesTest`) - fetch a page and wrap it as `LoadResult.Page`, with `prevKey`/`nextKey` null in whichever direction there's no more data.
2. **`MoviePagingSource.getRefreshKey`** (`T2RefreshKeyTest`) - after invalidation, use `PagingState.anchorPosition` and `closestPageToPosition` to work out which page to resume around.
3. **`loadAllMovies`** (`T3TestPagerTest`) - drive `MoviePagingSource` end to end with `paging-testing`'s `TestPager`: `refresh()`, then `append()` until it signals the end of pagination.
4. **`FlakyMoviePagingSource.load`** (`T4LoadErrorTest`) - turn a backend failure into `LoadResult.Error` instead of crashing, and let a later retry succeed.
5. **`moviePagingConfig`** (`T5PagingConfigTest`) - a `PagingConfig` that loads three pages' worth up front, starts prefetching halfway through a page, and disables placeholders.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
