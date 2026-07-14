# ImageCache

Practice module for the **Image loading (Glide/Coil-style pipeline)** topic
on Android Interview Prep.

You're building the pieces an image-loading library actually needs: a cache
key that folds in size and transformations (so a thumbnail and the full
image never collide), an LRU memory cache that evicts by *recency of use*
not insertion order, request coalescing so N concurrent requests for the
same image only trigger one network load, dropping a stale result when its
target gets recycled for different content, and the memory-then-disk-then-
network lookup order. Each task is a small function that's currently broken
or unwritten, with a matching test that's **red**. Make each test **green**,
one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :image-cache:test                          # run everything
./gradlew :image-cache:test --tests "*T1CacheKeyTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/imagecache/Tasks.kt`.

1. **`cacheKey`** (`T1CacheKeyTest`) - build a cache key that folds in the url, size and transformations, so a thumbnail and a full image never collide.
2. **`LruMemoryCache.put` / `.get`** (`T2BasicPutGetTest`) - basic storage: store, retrieve and overwrite.
3. **`LruMemoryCache.put` / `.get`, continued** (`T3LruEvictionTest`) - evict the least-recently-*used* entry when over the byte budget, where a `get()` counts as a use.
4. **`ImageRequestCoalescer.get`** (`T4RequestCoalescingTest`) - N concurrent requests for the same key trigger exactly one load; different keys and later calls each get their own.
5. **`loadInto`** (`T5StaleResultDroppedTest`) - drop an in-flight result if its target got rebound to different content before the load finished.
6. **`loadImage`** (`T6LayeredLookupTest`) - check memory, then disk, then network, stopping at the first hit and populating a faster layer via `onLoaded`.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
