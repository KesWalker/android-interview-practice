# HashMap Internals & Thread Safety

Practice module for the **HashMap Internals & Locks** topic on Android Interview Prep.

You're hardening a set of tiny map-backed helpers that are about to be shared
across threads: a lookup key, a hit counter, a compute-once cache, a
read/write-locked directory, a fixed-capacity LRU cache, and a safe way to
remove matching entries while iterating a map. Each task is a small type
that's currently broken or unwritten, with a matching test that's **red**.
Make each test **green**, one at a time, and you'll have written the HashMap
and concurrency idioms that come up constantly in interviews.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter
next to a test class, or from a terminal:

```bash
./gradlew :concurrent-collections:test                          # run everything
./gradlew :concurrent-collections:test --tests "*T1RoomKeyLookupTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/concurrentcoll/Tasks.kt`. Work out the
idiom yourself, or pair with the tutor and let it nudge you toward it.

1. **`RoomKey`** (`T1RoomKeyLookupTest`) — implement `equals`/`hashCode` so a `HashMap` finds an entry via a separate but equal key.
2. **`HitCounter`** (`T2HitCounterTest`) — record hits for a key without losing any when many threads call it at once.
3. **`ComputeOnceCache`** (`T3ComputeOnceCacheTest`) — compute a missing value once and reuse it, even when many threads ask for it at once.
4. **`SafeDirectory`** (`T4SafeDirectoryTest`) — let reads run alongside each other, but a write must exclude everything else.
5. **`LruCache`** (`T5LruCacheTest`) — evict the least-recently-used entry once capacity is exceeded, where a read counts as a use.
6. **`removeMatching`** (`T6RemoveMatchingTest`) — remove matching entries from a map while iterating it, without a ConcurrentModificationException.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
