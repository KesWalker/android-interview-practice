# BroadcastRouter

Practice module for the **Content Providers & Broadcasts** topic on Android
Interview Prep.

You're building the two pieces of pure matching/dispatch logic that sit
underneath `ContentResolver` and `BroadcastReceiver`: a content-URI matcher
that resolves an authority and path, with `#` and `*` wildcards, to a match
code the way `UriMatcher` does, and a broadcast router that decides which
registered receivers get an `Intent` based on their intent filters, whether
implicit broadcasts are even allowed to reach a manifest-declared receiver,
and how an ordered broadcast delivers by priority and can be cut short by
`abortBroadcast()`. Each task is a small function that's currently broken or
unwritten, with a matching test that's **red**. Make each test **green**,
one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :broadcast-router:test                          # run everything
./gradlew :broadcast-router:test --tests "*T1SegmentMatchesTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/broadcast/Tasks.kt`.

1. **`segmentMatches`** (`T1SegmentMatchesTest`) - the single-segment matching rule behind `#`, `*`, and literal path segments.
2. **`matchUri`** (`T2MatchUriTest`) - the full content-URI matcher: first registered pattern whose authority and path segments match wins.
3. **`filterMatches`** (`T3FilterMatchesTest`) - whether a broadcast intent matches an intent-filter's action, category subset and data scheme.
4. **`implicitBroadcastAllowed`** (`T4ImplicitBroadcastAllowedTest`) - why a manifest-declared receiver only sees implicit broadcasts for a small exempt set of actions, while a dynamically registered one sees them all.
5. **`matchingReceivers`** (`T5MatchingReceiversTest`) - combine filter matching and the implicit-broadcast restriction into the receivers that actually get a given intent.
6. **`orderedDispatch`** (`T6OrderedDispatchTest`) - the same matching, but delivered in priority order for an ordered broadcast.
7. **`dispatchWithAbort`** (`T7DispatchWithAbortTest`) - extend that ordering to simulate a receiver calling `abortBroadcast()`, stopping delivery to everything after it.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
