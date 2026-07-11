# Kotlin Collections & stdlib

Practice module for the **Kotlin 2.x, KSP & Collections** topic on Android Interview
Prep.

You're building small inventory and reporting helpers, and along the way you'll run
into the parts of Kotlin's collection interfaces and stdlib that trip people up in
interviews: what a read-only collection actually guarantees, how `List<T>`'s
variance lets you pass narrower numeric lists around, splitting a collection in one
pass, and generating values lazily instead of eagerly. Each task is a small
function or class that's currently unwritten, with a matching test that's **red**.
Make each test **green**, one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :collections:test                          # run everything
./gradlew :collections:test --tests "*T1CartViewTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/collections/Tasks.kt`.

1. **`Cart.items`** (`T1CartViewTest`) — expose the cart's items without copying the
   underlying list.
2. **`totalValue`** (`T2TotalValueTest`) — sum every value in a list of numbers, as a
   `Double`.
3. **`splitByLength`** (`T3SplitByLengthTest`) — split a list of words into two, by
   length, keeping order.
4. **`firstSquareOver`** (`T4FirstSquareOverTest`) — return the first perfect square
   past a threshold, without pre-computing every square up to it.
5. **`packInto`** (`T5PackIntoTest`) — copy a fixed-size Array into a genuinely
   growable list plus one more element.
6. **`frozen`** (`T6FrozenListTest`) — build a genuinely immutable persistent list,
   not just a read-only view over a mutable one.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
