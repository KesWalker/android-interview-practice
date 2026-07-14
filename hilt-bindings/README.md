# HiltBindings

Practice module for the **Dependency Injection with Hilt** topic on Android
Interview Prep.

You're building a tiny, generic stand-in for what Dagger/Hilt does at
compile time: a binding graph keyed by (type, qualifier), resolved by
constructor injection. Each task adds a piece of what makes that graph
production-grade: `@Binds`-style interface aliasing, qualifiers that tell
apart two bindings of the same type, an error that names exactly what's
missing, cycle detection, and the `Provider<T>` indirection real code uses to
break a cycle. Each task is a small function that's currently broken or
unwritten, with a matching test that's **red**. Make each test **green**,
one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :hilt-bindings:test                                   # run everything
./gradlew :hilt-bindings:test --tests "*T1ConstructorInjectionTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/bindings/Tasks.kt`.

1. **`resolve`** (`T1ConstructorInjectionTest`) - resolve a binding graph by constructor injection, recursing through dependencies in order.
2. **`resolveWithBinds`** (`T2BindsMappingTest`) - follow an `@Binds`-style alias from an interface key to its implementation key.
3. **`resolveQualified`** (`T3QualifierDisambiguationTest`) - resolve the exact (type, qualifier) binding requested, never a same-type binding under a different qualifier.
4. **`resolveOrThrow`** (`T4MissingBindingTest`) - throw an error naming the exact key that's missing, even several levels deep in the graph.
5. **`resolveDetectingCycles`** (`T5CycleDetectionTest`) - detect a binding that transitively depends on itself and report the cycle path.
6. **`resolveWithProviders`** (`T6ProviderBreaksCycleTest`) - wrap a chosen dependency in a lazy `Provider` instead of resolving it eagerly, the trick that breaks a cycle plain constructor injection can't.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
