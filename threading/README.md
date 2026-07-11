# Message Queue / Handler

Practice module for the **Looper, Handler & Threading** topic on Android Interview
Prep.

Android's `Looper`/`MessageQueue`/`Handler` trio is modelled here as plain Kotlin
so you can practice the idiom without any Android dependencies. You're building a
few small queue-like classes that a `Looper` and a `Handler` would use under the
hood. Each task is a class that's currently unwritten, with a matching test
that's **red**. Make each test **green**, one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :threading:test                       # run everything
./gradlew :threading:test --tests "*T1MessageLoopTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/threading/Tasks.kt`.

1. **`MessageLoop`** (`T1MessageLoopTest`) — drains posted actions in the order
   they arrived, including ones posted while it's already running.
2. **`BackgroundWorker`** (`T2BackgroundWorkerTest`) — runs posted actions one at
   a time on its own dedicated thread, safely accepting posts from any caller.
3. **`DelayedQueue`** (`T3DelayedQueueTest`) — schedules actions for a target
   time and runs only the ones that are due, earliest first.
4. **`StoppableQueue`** (`T4StoppableQueueTest`) — can be stopped by dropping
   everything, or by running what's already due first and dropping the rest.
5. **`IdleAwareQueue`** (`T5IdleAwareQueueTest`) — mirrors
   `MessageQueue.addIdleHandler`: fires an idle listener once, exactly when
   there's no more pending work left to run.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
