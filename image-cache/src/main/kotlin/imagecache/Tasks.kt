package imagecache

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.coroutineScope

/**
 * Image loading pipeline practice.
 *
 * Each function below is broken or unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to
 * implement it so its test goes GREEN. Run a single test class from the
 * gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :image-cache:test
 */

// TODO(t1): T1CacheKeyTest
// Build a cache key for an image request. It must include the url, the
// requested width and height, and the transformations, so a thumbnail and a
// full-size version of the same url never collide in the cache.
fun cacheKey(url: String, width: Int, height: Int, transformations: List<String> = emptyList()): String {
    TODO("t1: combine url, width, height and transformations into one key")
}

class LruMemoryCache(private val maxBytes: Int) {
    private val entries = LinkedHashMap<String, ByteArray>(16, 0.75f, true)
    private var currentBytes = 0

    // TODO(t2): T2BasicPutGetTest
    // TODO(t3): T3LruEvictionTest
    // Store `bytes` under `key`. Overwriting an existing key replaces its
    // bytes. If the total stored bytes would exceed maxBytes, evict entries
    // in least-recently-USED order (a get() counts as a use of that key)
    // until it fits again.
    fun put(key: String, bytes: ByteArray) {
        TODO("t2/t3: store bytes under key, evicting least-recently-used entries to stay within maxBytes")
    }

    // TODO(t2): T2BasicPutGetTest
    // TODO(t3): T3LruEvictionTest
    // Return the bytes stored under key, or null if absent. A successful
    // get counts as a use for LRU purposes.
    fun get(key: String): ByteArray? {
        TODO("t2/t3: look up key, marking it as recently used")
    }
}

class ImageRequestCoalescer(private val load: suspend (String) -> ByteArray) {
    private val inFlight = mutableMapOf<String, Deferred<ByteArray>>()

    // TODO(t4): T4RequestCoalescingTest
    // Load the image for `key`, but if a load for that same key is already
    // in flight, wait on that one instead of starting a second one. Once a
    // load for a key finishes, the next call for that key should start a
    // fresh load.
    suspend fun get(key: String): ByteArray = coroutineScope {
        TODO("t4: reuse an in-flight load for the same key instead of starting a second one")
    }
}

/** Where an in-flight load's result is being delivered. */
class ImageTarget(var currentRequestKey: String? = null)

// TODO(t5): T5StaleResultDroppedTest
// Bind `target` to `key`, then run `load`. If, once `load` finishes,
// `target` is still bound to `key`, return the loaded bytes. If the target
// has since been rebound to a different key (its cell was recycled for
// different content while this load was in flight), drop the result and
// return null.
suspend fun loadInto(target: ImageTarget, key: String, load: suspend (String) -> ByteArray): ByteArray? {
    TODO("t5: bind target to key, load, then return null if target was rebound before it finished")
}

// TODO(t6): T6LayeredLookupTest
// Look up an image in memory, then disk, then network, in that order,
// stopping at the first hit. When a result comes from disk or network,
// call `onLoaded` with it so a faster layer can be populated for next time.
// `onLoaded` is never called for a memory hit, since it's already there.
suspend fun loadImage(
    key: String,
    memory: () -> ByteArray?,
    disk: suspend () -> ByteArray?,
    network: suspend () -> ByteArray,
    onLoaded: (String, ByteArray) -> Unit
): ByteArray {
    TODO("t6: check memory, then disk, then network, populating via onLoaded on a disk or network hit")
}
