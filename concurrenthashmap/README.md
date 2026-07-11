# ConcurrentHashMap

Practice module for the **ConcurrentHashMap** topic on Android Interview Prep.

You're hardening a tiny shared-state helper library: a one-time registry, a hit
counter, a lazy cache, a leaderboard, an in-flight request tracker, and a
capacity-bounded registry, all backed by a single `ConcurrentHashMap` that
many threads hammer at once. Each task is a small function or type that's
currently unwritten, with a matching test that's **red** - some of those
tests fire hundreds of real threads at the same key to prove the naive
"check, then act" approach doesn't survive. Make each test **green**, one at
a time, and you'll have written the atomic-update idioms that come up
constantly in interviews.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter
next to a test class, or from a terminal:

```bash
./gradlew :concurrenthashmap:test                              # run everything
./gradlew :concurrenthashmap:test --tests "*PutIfAbsentOnceTest"  # one task
```

## The tasks

All the work is in `src/main/kotlin/chm/Tasks.kt`. Work out the idiom yourself,
or pair with the tutor and let it nudge you toward it.

1. **`putIfAbsentOnce`** (`PutIfAbsentOnceTest`) - insert a value only if the key is absent, and report whether this call was the one that did it.
2. **`incrementCount`** (`IncrementCountTest`) - bump a counter safely under concurrent callers.
3. **`getOrCompute`** (`GetOrComputeTest`) - lazily fill a cache entry without ever computing it twice for the same key.
4. **`bumpIfCurrent`** (`BumpIfCurrentTest`) - replace a value only if it still matches an expected value.
5. **`InFlightRequests`** (`InFlightRequestsTest`) - track in-flight ids with `ConcurrentHashMap.newKeySet()`, so no id is ever counted twice.
6. **`BoundedRegistry.register`** (`BoundedRegistryTest`) - accept registrations up to a fixed capacity, using an atomic counter instead of a racy `size()` check.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
