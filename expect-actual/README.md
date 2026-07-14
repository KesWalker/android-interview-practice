# ExpectActual

Practice module for the **Kotlin Multiplatform: expect/actual** topic on
Android Interview Prep.

A single JVM module can't host real `expect`/`actual` source sets, so this
models the pattern instead: `PlatformStore` is the "expect" contract,
`AndroidStore` and `IosStore` are two "actual" implementations of it, and
the tasks are the shared logic that only ever touches the interface, the
factory that resolves the right actual for a platform token, and the two
rules that keep shared code honestly platform-agnostic: catching a leaked
platform import, and deciding whether a given responsibility belongs in
commonMain at all. Each task is a small function that's currently broken or
unwritten, with a matching test that's **red**. Make each test **green**,
one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :expect-actual:test                              # run everything
./gradlew :expect-actual:test --tests "*T1BumpLaunchCountTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/expectactual/Tasks.kt`.

1. **`bumpLaunchCount`** (`T1BumpLaunchCountTest`) - shared logic written only against `PlatformStore`, proven identical across two injected actuals.
2. **`migrateLegacyKey`** (`T2MigrateLegacyKeyTest`) - a multi-step shared migration that still never touches a platform type.
3. **`providePlatformStore`** (`T3ProvidePlatformStoreTest`) - the factory that resolves the right actual for a platform token, the runtime stand-in for what the compiler does with `expect`/`actual`.
4. **`findPlatformLeaks`** (`T4FindPlatformLeaksTest`) - catch the design error KMP prevents: a platform-specific import sneaking into shared code.
5. **`requireCommonMainSafe`** (`T5RequireCommonMainSafeTest`) - turn that check into a hard failure with a useful message, the way a real lint gate would.
6. **`classifyResponsibility`** (`T6ClassifyResponsibilityTest`) - the boundary call itself: given a responsibility, does it belong in commonMain or a platform source set?

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
