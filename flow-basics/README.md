# Flow Basics

Practice module for the **Flow Basics** topic on Android Interview Prep.

You're building the small pieces that make up almost every real use of
Kotlin Flow: a cold producer that has no memory of previous collectors, an
operator chain that transforms and bounds a stream, a terminal collector
that turns a flow back into a plain value, the split between exceptions a
`.catch { }` can see and ones only your own `collect { }` block sees, and an
adapter that turns an old-school callback API into a Flow. Each task is a
small function that's currently broken or unwritten, with a matching test
that's **red**. Make each test **green**, one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :flow-basics:test                              # run everything
./gradlew :flow-basics:test --tests "*T1ColdFlowTest"     # one task
```

## The tasks

All the work is in `src/main/kotlin/flowbasics/Tasks.kt`.

1. **`countUpFlow`** (`T1ColdFlowTest`) - build a cold `flow { }` producer and prove it re-runs from scratch for every new collector.
2. **`firstEvenSquares`** (`T2MapFilterTakeTest`) - chain `map`/`filter`/`take`, including bounding an infinite upstream source.
3. **`runningTotals`** (`T3RunningTotalsTest`) - use a terminal operator to materialize a flow of running sums into a `List`.
4. **`collectWithFallback`** (`T4UpstreamCatchTest`) - use `.catch { }` to recover from an upstream exception without losing the values collected so far.
5. **`countUntilCollectorThrows`** (`T5CollectorThrowsTest`) - wrap `collect { }` in a try/catch to see why `.catch { }` never sees exceptions thrown inside your own collector lambda.
6. **`tickerFlow`** (`T6CallbackFlowTest`) - adapt a callback-based API into a Flow with `callbackFlow` and `awaitClose`.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
