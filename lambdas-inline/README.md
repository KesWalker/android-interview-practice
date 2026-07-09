# Lambdas & Inline Functions

Practice module for the **Lambdas & Inline Functions** topic on Android Interview Prep.

You're building a handful of small list/closure helpers, the kind that come up
constantly in Kotlin interviews once you're past the basics: trailing lambdas,
closures that capture and mutate outer state, non-local returns out of an
inlined lambda, and a reified generic that needs runtime type info. Each task
is a small function that's currently unwritten, with a matching test that's
**red**. Make each test **green**, one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :lambdas-inline:test                             # run everything
./gradlew :lambdas-inline:test --tests "*CounterTest"       # one task
```

## The tasks

All the work is in `src/main/kotlin/lambdas/Tasks.kt`. Work out the idiom yourself,
or pair with the tutor and let it nudge you toward it.

1. **`sumOfPositives`** (`SumOfPositivesTest`) — add up only the positive numbers in a list.
2. **`makeCounter`** (`CounterTest`) — return a callable that tracks its own call count.
3. **`indexOfFirstNegative`** (`IndexOfFirstNegativeTest`) — find the index of the first negative number, or -1.
4. **`filterIsInstance2`** (`FilterIsInstanceTest`) — keep only the elements of a mixed list that match a given type.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
