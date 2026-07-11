package concurrentcoll

/**
 * HashMap Internals & Thread Safety practice.
 *
 * Each type below is broken or unwritten and has a matching test in src/test
 * that is currently RED. Your job, one task at a time, is to make it correct
 * (and thread-safe where it matters) so its test goes GREEN. Run a single
 * test class from the gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :concurrent-collections:test
 */

// TODO(t1): T1RoomKeyLookupTest
// A map key type: two RoomKeys should be treated as the same key whenever
// their building and room match.
class RoomKey(val building: String, val room: Int) {
    override fun equals(other: Any?): Boolean {
        TODO("t1: true when other is a RoomKey with the same building and room")
    }

    override fun hashCode(): Int {
        TODO("t1: a hash that agrees with equals, so equal keys land in the same bucket")
    }
}

// TODO(t2): T2HitCounterTest
// A counter keyed by name; many threads may call record() for the same key
// at the same time and no hit should ever be lost.
class HitCounter {
    private val counts = mutableMapOf<String, Int>()

    fun record(key: String) {
        TODO("t2: add one hit for key without losing updates under concurrent calls")
    }

    fun countFor(key: String): Int = counts[key] ?: 0
}

// TODO(t3): T3ComputeOnceCacheTest
// A cache that computes a value for a key on first request and reuses it
// after that; concurrent requests for the same missing key must still only
// trigger one computation.
class ComputeOnceCache {
    private val values = mutableMapOf<String, Int>()

    fun getOrCompute(key: String, compute: (String) -> Int): Int {
        TODO("t3: return the cached value for key, computing it exactly once even under concurrent calls")
    }
}

// TODO(t4): T4SafeDirectoryTest
// A key/value directory that many threads read and write at once: writes
// must exclude everything else, but reads may run alongside other reads.
class SafeDirectory {
    private val entries = mutableMapOf<String, String>()

    fun put(key: String, value: String) {
        TODO("t4: write key/value while excluding all other readers and writers")
    }

    fun get(key: String): String? {
        TODO("t4: read key's value, allowed to run alongside other reads but never during a write")
    }

    fun size(): Int {
        TODO("t4: the current number of entries, read safely alongside other reads")
    }
}
