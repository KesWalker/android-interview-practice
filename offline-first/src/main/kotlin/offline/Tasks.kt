package offline

/**
 * Offline-First practice.
 *
 * Each piece below is broken or unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to make
 * the local store behave as the single source of truth, queue writes made
 * while offline, resolve conflicts when data comes back from the server, and
 * keep the UI state honest about what's cached versus what's failed.
 *
 *     ./gradlew :offline-first:test
 */

// TODO(t1): T1StaleWhileRevalidateTest
// Emit the cache's current value for `key` (if any) followed by the freshly
// fetched value, and leave the fresh value stored in the cache afterwards.
fun staleWhileRevalidate(
    cache: MutableMap<String, String>,
    key: String,
    fetchFresh: () -> String
): List<String> {
    TODO("t1: return cache[key] (when present) followed by fetchFresh()'s result, and write that fresh result into cache[key]")
}

class PendingWriteQueue<T> {
    private val items = mutableListOf<T>()

    // TODO(t2): T2PendingWriteQueueTest
    // Add a write to the end of the pending queue.
    fun enqueue(item: T) {
        TODO("t2: append item to the queue")
    }

    fun size(): Int = items.size

    // TODO(t2): T2PendingWriteQueueTest
    // Hand back every pending write in the order it was queued, then empty the queue.
    fun drain(): List<T> {
        TODO("t2: return all queued items in the order they were enqueued, and clear the queue")
    }
}

data class VersionedValue(val value: String, val timestamp: Long)

// TODO(t3): T3ConflictResolutionTest
// Given the local and remote versions of the same record, decide which one wins.
fun resolveConflict(local: VersionedValue, remote: VersionedValue): VersionedValue {
    TODO("t3: return whichever of local/remote has the newer timestamp; if the timestamps are equal, the remote (server) value wins")
}

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Content<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}

// TODO(t4): T4ToUiStateTest
// Decide what the UI should show given a possibly-cached value and a possible fetch error.
fun <T> toUiState(cached: T?, errorMessage: String?): UiState<T> {
    TODO("t4: show the cached value whenever one exists (even if errorMessage is set), otherwise show the error if there is one, otherwise show loading")
}
