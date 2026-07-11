# Generics & Variance

Practice module for the **Generics & Variance** topic on Android Interview Prep.

You're working with a tiny animal-shelter API built on generic types. Each task
is a small function that's currently broken or unwritten, with a matching test
that's **red**. Make each test **green**, one at a time, and you'll have written
the variance and reified-generics idioms the way they come up in interviews.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :generics-variance:test                         # run everything
./gradlew :generics-variance:test --tests "*T1FilterByTypeTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/generics/Tasks.kt`. Each one has a short,
idiomatic solution. Work it out yourself, or pair with the tutor and let it
nudge you toward it.

1. **`filterByType`** (`T1FilterByTypeTest`) — keep only the elements of a list that are actually a given type `T`.
2. **`widenToAnimalProducer`** (`T2WidenProducerTest`) — re-type a `Producer<Dog>` as a `Producer<Animal>`.
3. **`narrowToDogHandler`** (`T3NarrowHandlerTest`) — re-type an `AnimalHandler<Animal>` as an `AnimalHandler<Dog>`.
4. **`sumBoxes`** (`T4SumBoxesTest`) — sum the values held in a list of boxes whose exact element type isn't known.
5. **`maxByNaturalOrder`** (`T5MaxByNaturalOrderTest`) — return the greater of two values for a type parameter that needs a multi-bound `where` clause.
6. **`runTwiceWrapped`** (`T6RunTwiceWrappedTest`) — wrap a `crossinline` lambda in a `Runnable` that calls it twice when run.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
