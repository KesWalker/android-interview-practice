package threading

/**
 * Message Queue / Handler practice.
 *
 * Each class below is unwritten and has a matching test in src/test that is
 * currently RED. Your job, one task at a time, is to implement it so its test
 * goes GREEN. Run a single test class from the gutter in Android Studio, or
 * run them all with:
 *
 *     ./gradlew :threading:test
 */

// TODO(t1): MessageLoopTest
// A queue of pending actions. Running it executes every pending action in the
// order it was posted, and if an action posts more work while the queue is
// running, that new work still waits its turn instead of running immediately.
class MessageLoop {
    fun post(action: () -> Unit) {
        TODO("t1: remember this action so it runs on the next drain")
    }

    fun runToCompletion() {
        TODO("t1: run every pending action in posting order, including ones posted while running")
    }
}

// TODO(t2): BackgroundWorkerTest
// A worker with its own dedicated thread. Any caller, from any thread, can
// hand it an action; the worker runs every action it is given, one at a time,
// on that one dedicated thread.
class BackgroundWorker {
    fun start() {
        TODO("t2: start the dedicated thread")
    }

    fun post(action: () -> Unit) {
        TODO("t2: hand this action to the dedicated thread to run")
    }

    fun shutdown() {
        TODO("t2: stop the dedicated thread once it has finished what it was given")
    }
}

// TODO(t3): DelayedQueueTest
// A queue where every action is scheduled for a target time. Running it up to
// a given time executes only the actions whose target time has arrived,
// earliest target time first.
class DelayedQueue {
    fun schedule(dueAt: Long, action: () -> Unit) {
        TODO("t3: remember this action to run once `now` reaches dueAt")
    }

    fun runUpTo(now: Long) {
        TODO("t3: run every action whose dueAt is <= now, earliest first, then forget them")
    }
}

// TODO(t4): StoppableQueueTest
// A queue that can be stopped two different ways: drop every pending action
// immediately, or run whatever is already due and only then drop the rest.
class StoppableQueue {
    fun post(action: () -> Unit) {
        TODO("t4: queue this action to run as soon as possible")
    }

    fun postDelayed(dueAt: Long, action: () -> Unit) {
        TODO("t4: queue this action to run once `now` reaches dueAt")
    }

    fun quit() {
        TODO("t4: drop every pending action; run nothing")
    }

    fun quitSafely(now: Long) {
        TODO("t4: run every pending action already due by now, then drop whatever is left")
    }
}
