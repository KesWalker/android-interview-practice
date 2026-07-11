# Background Work Queue

Practice module for the **Services & Background Work** topic on Android Interview Prep.

You're building a tiny in-memory job queue that has to survive flaky work: jobs
run in the order they arrive, a failing job gets retried up to a limit before
the queue gives up on it, and retries wait a growing delay between attempts.
Each task is a small piece that's currently broken or unwritten, with a
matching test that's **red**. Make each test **green**, one at a time, and
you'll have modeled the same retry/backoff shape WorkManager gives you for
free.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter
next to a test class, or from a terminal:

```bash
./gradlew :work-queue:test                          # run everything
./gradlew :work-queue:test --tests "*BackoffDelayTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/work/Tasks.kt`.

1. **`enqueue` / `pendingIds`** (`EnqueueOrderTest`) — jobs wait in the order
   they were added, and running one takes it off the front.
2. **`runNext`** (`RetryUntilSuccessTest`) — retry a failing job's action until
   it succeeds, and report how many attempts it took.
3. **`runNext`** (`GiveUpAfterMaxAttemptsTest`) — stop retrying once
   `maxAttempts` is reached and report the failure instead of looping forever.
4. **`backoffMillis`** (`BackoffDelayTest`) — the delay before a given retry
   attempt, growing as attempts climb.
5. **`restartBehavior`** (`RestartBehaviorTest`) — what a killed started
   service does on restart, given its `onStartCommand` return flag
   (`START_STICKY` / `START_NOT_STICKY` / `START_REDELIVER_INTENT`).
6. **`canStartForegroundService`** (`CanStartForegroundServiceTest`) — whether
   the app is currently allowed to call `startForegroundService()`, given
   Android 12's background-start restriction.
7. **`constraintsSatisfied`** (`ConstraintsSatisfiedTest`) — whether a
   WorkManager job's declared constraints (network, charging, battery) are
   met by the device's current conditions.
8. **`chooseScheduler`** (`ChooseSchedulerTest`) — pick AlarmManager,
   WorkManager, or a plain coroutine for a job based on its timing and
   durability needs.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk
you through each one and tick them off as your tests go green.
