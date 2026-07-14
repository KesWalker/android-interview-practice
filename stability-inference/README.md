# StabilityInference

Practice module for the **Compose Performance** topic on Android Interview
Prep.

You're building a small model of the Compose compiler's stability inference:
given a type descriptor, decide whether it's stable or unstable, use that
to decide whether a composable is skippable, and apply Strong Skipping's
comparison rule (unstable params compare by reference, stable params by
content). Each task is a small function that's currently broken or
unwritten, with a matching test that's **red**. Make each test **green**,
one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :stability-inference:test                          # run everything
./gradlew :stability-inference:test --tests "*T1InferStabilityTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/stability/Tasks.kt`.

1. **`inferStability`** (`T1InferStabilityTest`) - classify a type descriptor as stable or unstable: primitives are always stable, interfaces like `List` are unstable unless annotated, and a class is unstable if it has a `var` field or an unstable field.
2. **`isSkippable`** (`T2IsSkippableTest`) - a composable is skippable only if every one of its param types is stable.
3. **`strongSkippingEquals`** (`T3StrongSkippingEqualsTest`) - compare old vs. new arguments the way Strong Skipping does: `==` for stable types, `===` for unstable types.
4. **`lambdaIsStable`** (`T4LambdaIsStableTest`) - a lambda is stable only if everything it captures is stable.
5. **`shouldRecompose`** (`T5ShouldRecomposeTest`) - combine skippability with the per-param comparison to decide whether a call site actually needs to recompose.
6. **`countRecompositions`** (`T6RecompositionCountTest`) - apply that decision across a sequence of recompositions to count how many actually happened.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
