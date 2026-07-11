# Scope Functions

Practice module for the **Scope Functions** topic on Android Interview Prep.

You're writing a handful of small helpers around a report/order model. Each task
is a small function that's currently unwritten, with a matching test that's
**red**. Make each test **green**, one at a time, and you'll have practiced
picking the right scope function the way it comes up in interviews — what it
returns (the lambda result vs. the context object) and how it references the
object (`this` vs. `it`).

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :scope-functions:test                              # run everything
./gradlew :scope-functions:test --tests "*GreetIfPresentTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/scopefunctions/Tasks.kt`. Each one has a
short, idiomatic solution. Work out the right scope function yourself, or pair
with the tutor and let it nudge you toward it.

1. **`greetIfPresent`** (`GreetIfPresentTest`) — greet by name, or `null` when it's null/blank.
2. **`buildPublishedReport`** (`BuildPublishedReportTest`) — build and return a configured, published `Report`.
3. **`trackAndDouble`** (`TrackAndDoubleTest`) — record a number as a side effect, then return it doubled.
4. **`summarize`** (`SummarizeTest`) — build a one-line summary from an order's own properties, or `null` when the order is `null`.
5. **`reportLabel`** (`ReportLabelTest`) — build a report's label, reading every value straight off `report` with `with`.
6. **`scoreBand`** (`ScoreBandTest`) — compute a score's letter band as a local step, then build the label, with `run`.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
