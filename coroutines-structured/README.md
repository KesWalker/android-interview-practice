# Structured Concurrency & Cancellation

Practice module for the **Structured Concurrency & Cancellation** topic on Android Interview Prep.

You're building a small set of coroutine helpers that stand in for the kind of
concurrent plumbing that shows up in real apps: running several tasks
together and failing atomically, guaranteeing cleanup no matter what happens,
isolating one failure from its siblings, and combining parallel results
without losing any of them. Each task is a small function that's currently
unwritten, with a matching test that's **red**. Make each test **green**, one
at a time, and you'll have written the structured-concurrency idioms that
come up constantly in interviews.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter
next to a test class, or from a terminal:

```bash
./gradlew :coroutines-structured:test                              # run everything
./gradlew :coroutines-structured:test --tests "*RunTogetherOrFailAllTest"  # one task
```

## The tasks

All the work is in `src/main/kotlin/structured/Tasks.kt`. Work out the idiom
yourself, or pair with the tutor and let it nudge you toward it.

1. **`runTogetherOrFailAll`** (`RunTogetherOrFailAllTest`) — run every task at once; one failure fails the call and stops the rest.
2. **`processWithCleanup`** (`ProcessWithCleanupTest`) — run work, then always run cleanup, even on failure or cancellation.
3. **`runIsolatedReporting`** (`RunIsolatedReportingTest`) — run every task at once, routing a failure to a callback without stopping the others.
4. **`loadAllResults`** (`LoadAllResultsTest`) — run every task at once, collecting a success-or-failure result per task in order.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
