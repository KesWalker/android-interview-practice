# StateHoisting

Practice module for the **State Hoisting** topic on Android Interview Prep.

You're building the plumbing that makes `by remember { mutableStateOf(...) }`
and state hoisting work, without Compose: a state cell that supports
property delegation, one that only notifies on a real change, a
`remember`-alike cache keyed by its inputs, a `rememberSaveable`-alike that
restores a single slot from a saved bundle, and finally the actual hoisting
rule, computing which node in a tree should own a piece of state given who
reads it and who writes it. Each task is a small function that's currently
broken or unwritten, with a matching test that's **red**. Make each test
**green**, one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :state-hoisting:test                          # run everything
./gradlew :state-hoisting:test --tests "*T1DelegateTest"  # one task
```

## The tasks

All the work is in `src/main/kotlin/hoisting/Tasks.kt`.

1. **`MutableStateLike.getValue`/`setValue`** (`T1DelegateTest`) - implement property delegation so an instance can be read and written with `by`.
2. **`ObservableState.update`** (`T2NotifyOnChangeTest`) - notify observers only when the new value actually differs from the current one.
3. **`RememberCache.getOrCompute`** (`T3RememberTest`) - recompute a cached value only when its keys change since the last call.
4. **`rememberSaveable`** (`T4RememberSaveableTest`) - restore a single slot from a saved bundle when present and safe to persist, otherwise fall back to an initial value.
5. **`lowestCommonAncestor`** (`T5LowestCommonAncestorTest`) - find the deepest shared ancestor of a set of tree nodes.
6. **`resolveStateOwner`** (`T6ResolveOwnerTest`) - apply the actual hoisting rule: the owner must be an ancestor of every reader and every writer.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
