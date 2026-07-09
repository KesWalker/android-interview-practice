# Data, Sealed & Value Classes

Practice module for the **Data, Sealed & Value Classes** topic on Android Interview Prep.

You're modeling a small order-tracking system: an employee record, a point on a
grid, an order's delivery lifecycle, and a distance measurement. Each task is a
small function that's currently unwritten, with a matching test that's **red**.
Make each test **green**, one at a time, and you'll have exercised the modeling
primitives Kotlin interviewers ask about most.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :kotlin-classes:test                                # run everything
./gradlew :kotlin-classes:test --tests "*EmployeeEqualityTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/classes/Tasks.kt`. Each solution is short - work
out the idiom yourself, or pair with the tutor and let it nudge you toward it.

1. **`sameEmployee`** (`EmployeeEqualityTest`) - decide equality for two employee records where one property lives outside the primary constructor.
2. **`moved`** (`MovedPointTest`) - produce a shifted copy of a point without touching the original.
3. **`statusMessage`** (`DeliveryStatusMessageTest`) - map every stage of a closed delivery-status hierarchy to its message.
4. **`doubled`** (`DoubleDistanceTest`) - double a wrapped distance value and hand back the same wrapper type.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
