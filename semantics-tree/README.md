# Semantics Tree

Practice module for the **UI Testing** topic on Android Interview Prep.

You're building the matcher engine underneath a UI test framework like Compose UI
testing or Espresso: a tree walk, matcher combinators, the merged-vs-unmerged text
rule accessibility services rely on, a finder that fails loudly on ambiguity instead
of silently picking a node, a couple of assertion helpers, and the idling rule that
lets a test wait for real quiescence instead of sleeping. Each task is a small
function that's currently broken or unwritten, with a matching test that's **red**.
Make each test **green**, one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :semantics-tree:test                         # run everything
./gradlew :semantics-tree:test --tests "*T1FlattenTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/semantics/Tasks.kt`.

1. **`flatten`** (`T1FlattenTest`) - walk a semantics tree in pre-order, collecting every node.
2. **`and` / `or` / `not`** (`T2MatcherCombinatorsTest`) - the combinators every finder is built from.
3. **`mergedText`** (`T3MergedTextTest`) - report a node's text unmerged, or joined with its subtree when it merges descendants.
4. **`findNode`** (`T4FindNodeTest`) - the core finder, throws a clear error on zero matches or more than one.
5. **`onNodeWithText` / `onNodeWithTag`** (`T5FindersTest`) - the convenience finders wired onto `findNode`.
6. **`assertHasText` / `assertIsEnabled`** (`T6AssertionsTest`) - assertion helpers a test calls on a node it already found.
7. **`waitForIdle`** (`T7WaitForIdleTest`) - drain pending work until the UI is quiescent, or fail loudly instead of sleeping.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
