# Doze Scheduler

Practice module for the **Battery & Performance** topic on Android Interview
Prep.

You're building the decision logic behind Doze and App Standby: detecting
when the device has gone idle enough to enter Doze, deciding whether a job
may run immediately or has to wait, and batching every deferred job into a
shared maintenance window instead of waking the radio once per job, which is
the actual battery win Doze buys you. Each task is a small function that's
currently broken or unwritten, with a matching test that's **red**. Make each
test **green**, one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run
gutter next to a test class, or from a terminal:

```bash
./gradlew :doze-scheduler:test                            # run everything
./gradlew :doze-scheduler:test --tests "*T4BatchJobsTest"  # one task
```

## The tasks

All the work is in `src/main/kotlin/doze/Tasks.kt`.

1. **`isDozing`** (`T1IsDozingTest`) - decide whether the device has entered
   Doze from its screen, charging, motion, and idle-time state.
2. **`canRunImmediately`** (`T2CanRunImmediatelyTest`) - decide whether a job
   may run right now or must wait for the next maintenance window, based on
   whether the device is dozing and the job's priority.
3. **`nextMaintenanceWindow`** (`T3NextMaintenanceWindowTest`) - round a
   job's arrival time up to the next maintenance window boundary.
4. **`batchJobs`** (`T4BatchJobsTest`) - batch every deferred job into its
   maintenance window and count how many radio wakeups that batching saved
   versus waking the radio once per job.
5. **`jobMayRunNow`** (`T5JobMayRunNowTest`) - combine the Doze/priority
   check with a job's declared constraints (requires-charging,
   requires-unmetered-network) into one eligibility decision.
6. **`backoffForAttempt`** (`T6BackoffForAttemptTest`) - exponential backoff
   for a failing job, doubling per attempt but capped.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk
you through each one and tick them off as your tests go green.
