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

// TODO(t1): PutIfAbsentOnceTest
// Insert `value` under `key` only if the key isn't already present; return
// whether this call was the one that inserted it.
fun putIfAbsentOnce(map: ConcurrentHashMap<String, Int>, key: String, value: Int): Boolean {
    TODO("t1: insert value for key only if absent, return true iff this call inserted it")
}

// TODO(t2): IncrementCountTest
// Bump the counter stored under `key` by one (starting from 0 if it isn't
// there yet) and return its new value, safe under concurrent callers.
fun incrementCount(counts: ConcurrentHashMap<String, Int>, key: String): Int {
    TODO("t2: increment the counter under key and return the new value, safely under concurrent calls")
}

// TODO(t3): GetOrComputeTest
// Return the value already stored under `key`, or otherwise compute it with
// `compute`, store it, and return that - running `compute` at most once per key
// even when many callers ask for the same key at the same time.
fun <K, V> ConcurrentHashMap<K, V>.getOrCompute(key: K, compute: () -> V): V {
    TODO("t3: return the stored value for key, else compute it once, store it, and return it")
}

// TODO(t4): BumpIfCurrentTest
// Replace the value under `key` with `updated`, but only if it's still equal
// to `expected`; return whether the replacement happened.
fun bumpIfCurrent(scores: ConcurrentHashMap<String, Int>, key: String, expected: Int, updated: Int): Boolean {
    TODO("t4: replace key's value with updated only if it currently equals expected")
}
