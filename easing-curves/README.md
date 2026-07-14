# Easing

Practice module for the **Animation: easing and motion** topic on Android
Interview Prep.

You're building the pure math behind Compose animation: linear interpolation,
a point on a cubic Bezier curve, a keyframe tween sampled at an arbitrary
time and clamped at the ends, a real cubic-bezier easing curve solved by
binary search (the same idea Compose's `CubicBezierEasing` uses), a damped
spring stepped forward one frame at a time, and the interruption rule that a
retargeted spring must continue from its current value and velocity, never
snap either one to zero. Each task is a small function that's currently
broken or unwritten, with a matching test that's **red**. Make each test
**green**, one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :easing-curves:test                          # run everything
./gradlew :easing-curves:test --tests "*T1LerpTest"     # one task
```

## The tasks

All the work is in `src/main/kotlin/easing/Tasks.kt`.

1. **`lerp`** (`T1LerpTest`) - linear interpolation, the building block for everything else.
2. **`cubicBezierPoint`** (`T2CubicBezierPointTest`) - a point on a cubic Bezier curve at a given parameter t.
3. **`sampleTween`** (`T3SampleTweenTest`) - sample a keyframe tween at an arbitrary time, clamped at the ends.
4. **`cubicBezierEase`** (`T4CubicBezierEaseTest`) - a real easing curve solved by binary search, the same technique Compose's CubicBezierEasing uses.
5. **`springStep`** (`T5SpringStepTest`) - one physics step of a damped spring, tuneable from critically damped to bouncy.
6. **`runInterruptedSpring`** (`T6RetargetSpringTest`) - retargeting a running spring mid-flight without snapping its value or velocity, the bug behind every "animation jump."

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
