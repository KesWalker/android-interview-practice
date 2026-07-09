# Null Safety

Practice module for the **Null Safety** topic on Android Interview Prep.

You're hardening a tiny user-profile helper that keeps crashing with
`NullPointerException`s. Each task is a small function that's currently broken or
unwritten, with a matching test that's **red**. Make each test **green**, one at a
time, and you'll have written idiomatic null-safe Kotlin the way it comes up in
interviews.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :null-safety:test                       # run everything
./gradlew :null-safety:test --tests "*SafeLengthTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/nullsafety/Tasks.kt`. No `!!` allowed — each one
has an idiomatic one-liner. Work out the idiom yourself, or pair with the tutor and
let it nudge you toward it.

1. **`safeLength`** (`SafeLengthTest`) — length of a nullable string, or 0.
2. **`greetingFor`** (`GreetingTest`) — greet by name, fall back when null or blank.
3. **`validAges`** (`ValidAgesTest`) — keep only the strings that parse as `Int`.
4. **`shout`** (`ShoutTest`) — uppercase only when it's a `String`, else null.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
