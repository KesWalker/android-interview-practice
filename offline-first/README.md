# Offline-First

Practice module for the **Offline-First** topic on Android Interview Prep.

You're building the pieces that make a note-taking app work without a network
connection: a cache that's the single source of truth, a queue for writes made
while offline, a rule for resolving conflicts when the server and the device
disagree, and a UI state that falls back to cached data instead of erroring
out. Each task is a small piece that's currently broken or unwritten, with a
matching test that's **red**. Make each test **green**, one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :offline-first:test                                 # run everything
./gradlew :offline-first:test --tests "*PendingWriteQueueTest"  # one task
```

## The tasks

All the work is in `src/main/kotlin/offline/Tasks.kt`.

1. **`staleWhileRevalidate`** (`StaleWhileRevalidateTest`) — serve the cached value immediately, then refresh it from the network and update the cache so it stays the single source of truth.
2. **`PendingWriteQueue.enqueue` / `.drain`** (`PendingWriteQueueTest`) — queue writes made while offline and flush them, in order, when the app is ready to sync.
3. **`resolveConflict`** (`ConflictResolutionTest`) — pick the winning version between a local and a remote edit of the same record.
4. **`toUiState`** (`ToUiStateTest`) — turn a cached value plus an optional fetch error into the state the UI should render.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
