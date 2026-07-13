package chm

import java.util.concurrent.ConcurrentHashMap

/**
 * ConcurrentHashMap practice.
 *
 * Each function below is unwritten and has a matching test in src/test that is
 * currently RED. Your job, one task at a time, is to implement it idiomatically
 * so its test goes GREEN. Run a single test class from the gutter in Android
 * Studio, or run them all with:
 *
 *     ./gradlew :concurrenthashmap:test
 */

// TODO(t1): T1PutIfAbsentOnceTest
// Insert `value` under `key` only if the key isn't already present; return
// whether this call was the one that inserted it.
fun putIfAbsentOnce(map: ConcurrentHashMap<String, Int>, key: String, value: Int): Boolean {
    TODO("t1: insert value for key only if absent, return true iff this call inserted it")
}

// TODO(t2): T2IncrementCountTest
// Bump the counter stored under `key` by one (starting from 0 if it isn't
// there yet) and return its new value, safe under concurrent callers.
fun incrementCount(counts: ConcurrentHashMap<String, Int>, key: String): Int {
    TODO("t2: increment the counter under key and return the new value, safely under concurrent calls")
}

// TODO(t3): T3GetOrComputeTest
// Return the value already stored under `key`, or otherwise compute it with
// `compute`, store it, and return that - running `compute` at most once per key
// even when many callers ask for the same key at the same time.
fun <K, V> ConcurrentHashMap<K, V>.getOrCompute(key: K, compute: () -> V): V {
    TODO("t3: return the stored value for key, else compute it once, store it, and return it")
}

// TODO(t4): T4BumpIfCurrentTest
// Replace the value under `key` with `updated`, but only if it's still equal
// to `expected`; return whether the replacement happened.
fun bumpIfCurrent(scores: ConcurrentHashMap<String, Int>, key: String, expected: Int, updated: Int): Boolean {
    TODO("t4: replace key's value with updated only if it currently equals expected")
}

// TODO(t5): T5InFlightRequestsTest
// A concurrent set of in-flight request ids. start() adds an id and returns
// true only for the caller that actually added it (false if already in flight);
// finish() removes it; count() reports how many are currently in flight.
class InFlightRequests {
    fun start(id: String): Boolean {
        TODO("t5: add id, returning true only if this call was the one that added it")
    }

    fun finish(id: String) {
        TODO("t5: remove id from the in-flight set")
    }

    fun count(): Int {
        TODO("t5: return the number of ids currently in flight")
    }
}

// TODO(t6): T6BoundedRegistryTest
// A registry that accepts new key/value entries only while under `capacity`.
// register() returns true when the entry was stored, false once full; even under
// heavy concurrency exactly `capacity` registrations succeed. size() is the count.
class BoundedRegistry(private val capacity: Int) {
    fun register(key: String, value: String): Boolean {
        TODO("t6: store key/value while under capacity, returning true iff it was stored; reject once full")
    }

    fun size(): Int {
        TODO("t6: return the number of registered entries")
    }
}
