package jmm

/**
 * JVM Memory Model practice.
 *
 * Each type below is broken or unwritten and has a matching test in src/test
 * that is currently RED. Your job, one task at a time, is to make it safe for
 * concurrent callers so its test goes GREEN. Run a single test class from the
 * gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :jmm:test
 */

// TODO(t1): T1SafeCounterTest
// A counter that many threads can increment at the same time without losing updates.
class SafeCounter {
    fun increment() {
        TODO("t1: increase the count by one; concurrent callers must never lose an update")
    }

    fun get(): Int {
        TODO("t1: return the current count")
    }
}

// TODO(t2): T2HighWaterMarkTest
// Remembers the highest value it has ever seen, even when many threads record at once.
class HighWaterMark {
    fun record(value: Int) {
        TODO("t2: remember value if it's higher than anything seen so far")
    }

    fun current(): Int {
        TODO("t2: return the highest value recorded so far")
    }
}

// TODO(t3): T3LazyOnceTest
// Computes its value on first use and reuses it afterwards, even if several
// threads ask for it before it's ready.
class LazyOnce<T>(private val compute: () -> T) {
    fun value(): T {
        TODO("t3: compute the value once, caching it so later/concurrent callers reuse it")
    }
}

// TODO(t4): T4RunOnBackgroundThreadTest
// Runs `work` on its own thread and hands the result back to the caller.
fun <T> runOnBackgroundThread(work: () -> T): T {
    TODO("t4: run work on its own thread, wait for it to finish, and return its result")
}
