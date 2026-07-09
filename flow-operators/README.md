# Flow & Operators

Practice module for the **Flow & Operators** topic on Android Interview Prep.

You're building a handful of small `Flow` helpers that stand in for the kind
of reactive plumbing that shows up constantly on modern Android: a producer
that must re-run for every collector, a combiner that always uses each
source's latest value, a recovery path that respects exception transparency,
and a search-as-you-type helper that abandons stale work. Each task is a
small function that's currently unwritten, with a matching test that's
**red**. Make each test **green**, one at a time, and you'll have written the
`Flow` idioms that come up constantly in interviews.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter
next to a test class, or from a terminal:

```bash
./gradlew :flow-operators:test                          # run everything
./gradlew :flow-operators:test --tests "*ColdNumbersTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/flow/Tasks.kt`. Work out the idiom
yourself, or pair with the tutor and let it nudge you toward it.

1. **`coldNumbers`** (`ColdNumbersTest`) — emit 1, 2, 3, calling a callback at the start of every collection.
2. **`latestPair`** (`LatestPairTest`) — pair whichever source just emitted with the other's most recent value.
3. **`safeDivideFlow`** (`SafeDivideFlowTest`) — emit divided values, recovering a divide-by-zero into -1.
4. **`searchResults`** (`SearchResultsTest`) — search on each query, abandoning any still-running older search.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
