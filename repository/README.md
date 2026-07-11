# Repository & Data Layer

Practice module for the **Repository & Data Layer** topic on Android Interview Prep.

You're building the data layer for a small article app: a repository that maps
network shapes to domain models, keeps a local store as its single source of
truth, stays main-safe, and guards a shared cache from concurrent callers.
Each task is a small function or class that's currently unwritten, with a
matching test that's **red**. Make each test **green**, one at a time, and
you'll have written idiomatic Kotlin the way this topic comes up in
interviews.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :repository:test                          # run everything
./gradlew :repository:test --tests "*T1FetchArticleTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/repository/Tasks.kt`. Work out the idiom
yourself, or pair with the tutor and let it nudge you toward it.

1. **`fetchArticle`** (`T1FetchArticleTest`) — fetch a DTO and return just the fields the app needs, as a domain model.
2. **`refreshArticles`** (`T2RefreshArticlesTest`) — fetch, trim, and save into the local store, which is the single source of truth.
3. **`loadOnDispatcher`** (`T3LoadOnDispatcherTest`) — run blocking work on an injected dispatcher instead of the caller's context.
4. **`ArticleCache`** (`T4ArticleCacheTest`) — record hits on a shared cache without losing updates under concurrent access.
5. **`observeArticlesByQuery`** (`T5ObserveArticlesByQueryTest`) — expose an ongoingly-updating filtered Flow instead of a one-shot suspend function.
6. **`logEvent`** (`T6LogEventTest`) — launch app-scoped work that survives the caller's own scope being cancelled.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
