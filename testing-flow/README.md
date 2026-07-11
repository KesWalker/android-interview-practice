# Testing Coroutines & Flow

Practice module for the **Testing Coroutines & Flow** topic on Android Interview Prep.

You're hardening the test suite for a small set of coroutine- and Flow-backed
helpers that stand in for real Android testing scenarios: a loader with an
injected dispatcher, a presenter that hangs off `Dispatchers.Main`, a Flow
transformation, and a never-ending ticker. Each task is a small piece that's
currently unwritten, with a matching test that's **red**. Make each test
**green**, one at a time, and you'll have practiced the deterministic
coroutine-testing idioms that come up constantly in interviews: virtual time,
test dispatchers, `Dispatchers.setMain`/`resetMain`, and collecting Flow
emissions without ever touching a real clock or thread.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter
next to a test class, or from a terminal:

```bash
./gradlew :testing-flow:test                          # run everything
./gradlew :testing-flow:test --tests "*T1GreetingLoaderTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/testflow/Tasks.kt`. Work out the idiom
yourself, or pair with the tutor and let it nudge you toward it.

1. **`GreetingLoader.load`** (`T1GreetingLoaderTest`) — fetch a greeting on an injected dispatcher and publish it to state.
2. **`SearchPresenter.search`** (`T2SearchPresenterTest`) — report loading around a repository call, then publish its results.
3. **`aboveThreshold`** (`T3AboveThresholdTest`) — keep only the Flow values above a threshold.
4. **`ticker`** (`T4TickerTest`) — emit an incrementing count forever, on a fixed interval.
5. **`AuditLogger.start`** (`T5EagerAuditLogTest`) — start collecting a Flow the
   moment `start()` is called; the test contrasts `UnconfinedTestDispatcher`'s
   eager dispatch against `StandardTestDispatcher`'s queued dispatch for the
   exact same code.
6. **`resultsFlow`** (`T6ResultsFlowTest`) — emit a finite list as a Flow, tested
   with [Turbine](https://github.com/cashapp/turbine)'s `test { }` DSL
   (`awaitItem`/`awaitComplete`) instead of a plain collected list.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
