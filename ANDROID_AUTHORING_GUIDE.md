# Real-Compose Practice Module Authoring Guide

You are authoring a **real Jetpack Compose** practice exercise. Unlike the pure-Kotlin
modules in this repo, these modules use the **actual Compose APIs**, because for these
topics the API surface IS the skill being tested.

Repo (a git worktree, work ONLY here):
`/Users/keswalker/VsCodeProjects/android-interview-practice-android`

## What already works (do NOT re-invent or change it)

Your module dir, `build.gradle.kts`, `settings.gradle.kts` include, and a
`PreviewScreenshotTest.kt` ALREADY EXIST and are verified working. Do not edit
`settings.gradle.kts`, `gradle.properties`, the root `build.gradle.kts`, or your
module's `build.gradle.kts` (unless you genuinely need one extra dependency, in which
case add just that line).

The infrastructure gives you three loops, all off-device (Robolectric, no emulator):
1. **Learner sees the UI**: they write a `@Preview`, Android Studio renders it live.
2. **Tests assert behaviour**: Robolectric + `createComposeRule()` semantics assertions.
3. **The AI tutor sees the UI**: `./gradlew :<mod>:recordRoborazziDebug` renders EVERY
   `@Preview` in the module to `build/previews/<PreviewName>.png`, which the tutor reads.
   `PreviewScreenshotTest` is resilient: an unimplemented task's preview is skipped as
   PENDING and the rest still render, so previews appear progressively as tasks are done.

## Your job

Write, in your module:

- `src/main/kotlin/<pkg>/Tasks.kt` - the real `@Composable` task stubs.
- `src/main/kotlin/<pkg>/Previews.kt` - one `@Preview` per task.
- `src/test/kotlin/<pkg>/T<N><Name>Test.kt` - one Robolectric Compose test class per task.
- `README.md` - scenario framing + how to run + numbered task list.

**5-8 tasks.** Every task needs BOTH a stub in Tasks.kt AND its own test class, or the
module will not compile. This is the #1 rule (a past pass shipped tests without stubs and
broke 33 modules).

### Stub shape

Composable task stubs must COMPILE but not work. Use `TODO()` for the body:

```kotlin
// TODO(t1): T1CounterTest
// Show the count and an "Increment" button that raises it. The count must
// survive recomposition (this is the whole point of the task).
@Composable
fun Counter(modifier: Modifier = Modifier) {
    TODO("t1: hold count in remember { mutableStateOf(0) } and render it")
}
```

For a task whose point is a NON-Composable helper (a stability rule, a state holder), a
plain function stub is fine too.

### Previews (Previews.kt)

One `@Preview` per task, named `<Task>Preview`, wrapping the learner's composable in a
theme so it renders standalone:

```kotlin
@Preview(showBackground = true, widthDp = 240, heightDp = 160)
@Composable
fun CounterPreview() {
    MaterialTheme { Surface { Counter() } }
}
```
Give each preview an explicit `widthDp`/`heightDp` so the rendered PNG is a sensible size.

### Tests (JUnit 4 + Robolectric, NOT JUnit 5)

These modules use **JUnit 4** (`org.junit.Test`, `@RunWith`), because the Compose test rule
requires it. Copy this header exactly:

```kotlin
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w360dp-h640dp-xhdpi")
class T1CounterTest {
    @get:Rule val compose = createComposeRule()

    @Test fun `count starts at zero and increments on click`() {
        compose.setContent { MaterialTheme { Counter() } }
        compose.onNodeWithText("Count: 0").assertIsDisplayed()
        compose.onNodeWithText("Increment").performClick()
        compose.onNodeWithText("Count: 1").assertIsDisplayed()
    }
}
```

Assert with the **semantics tree** (`onNodeWithText`, `onNodeWithTag`, `assertIsDisplayed`,
`performClick`, `assertTextEquals`, `onNodeWithContentDescription`). For a recomposition-count
task, count recompositions with a side-effect counter rather than a screenshot.

Tests must be deterministic: no real delays. For animation/time, drive the clock with
`compose.mainClock.autoAdvance = false` and `compose.mainClock.advanceTimeBy(ms)`.

Tests must be RED against the stubs, failing with `NotImplementedError` from `TODO()`,
never a compile error.

## Definition of done (verify, do not assume)

1. `./gradlew :<mod>:compileDebugUnitTestKotlin` PASSES (proves stubs match tests).
2. **SOLVABILITY CHECK (mandatory):** temporarily write a correct reference implementation
   of every task, run `./gradlew :<mod>:testDebugUnitTest` and confirm EVERY test goes
   GREEN. This proves the tests are satisfiable and your expected values are right.
3. Still with the reference implementation in place, run
   `./gradlew :<mod>:recordRoborazziDebug` and confirm a PNG appears in
   `build/previews/` for EVERY preview. **Look at the PNGs** (read the image files) and
   confirm each actually shows the intended UI, not an empty or broken frame.
4. **REVERT** `Tasks.kt` to the `TODO()` stubs. Re-confirm the module still compiles and
   the tests are red with `NotImplementedError` only. Leave the repo in the RED state.
5. Delete any PNGs your reference run produced (`rm -rf <mod>/build/previews`) so no
   solved screenshots are committed. (`build/` is gitignored anyway.)

Gradle note: builds in this repo serialise on a project lock. If gradle reports a lock
timeout because another agent is building, wait a few seconds and retry.

## Style

- Never use the "—" em-dash character.
- Kotlin block comments NEST: a literal `/*` inside a comment breaks the file with
  "Unclosed comment". Avoid `/*` in comments.
- Match the existing repo tone: friendly, concrete, second person.
- Tasks build easy to hard; the last task is one an interviewer would really ask.

## The JSON spec (the app's Pair tab)

Also write `/Users/keswalker/VsCodeProjects/android-interview-prep/pair-staging-android/<topic-id>.json`
(create the dir if needed) with:

```json
{
  "repoPath": "<mod>",
  "repoUrl": "https://github.com/KesWalker/android-interview-practice",
  "intro": "Second-person framing. Mention that each task has a failing test to turn green, and that they can see their UI live in the Android Studio preview.",
  "tasks": [
    { "id": "t1", "title": "Counter - state that survives recomposition",
      "detail": "What to build.",
      "hint": "Coach-only nudge, never shown to the learner.",
      "test": "T1CounterTest",
      "verify": "T1CounterTest is green and the Counter preview renders the count and button." }
  ]
}
```
`test` MUST exactly equal the test class name on disk. Task count == stub count == test count.
