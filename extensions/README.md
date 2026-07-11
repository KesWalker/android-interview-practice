# Extension Functions & Properties

Practice module for the **Extension Functions & Properties** topic on Android Interview
Prep.

You're building a handful of small helpers the way Kotlin (and Android KTX) encourages:
bolting extra behavior onto types you don't control, without subclassing them. Each task
is a small extension that's currently unwritten, with a matching test that's **red**.
Make each test **green**, one at a time, and you'll have written the exact idioms
interviewers probe for: nullable receivers, extension properties, operator extensions,
and companion-object factories.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :extensions:test                          # run everything
./gradlew :extensions:test --tests "*DisplayNameTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/extensions/Tasks.kt`. No `!!` allowed — each one
has a short idiomatic solution. Work it out yourself, or pair with the tutor and let
it nudge you toward the idiom.

1. **`displayNameOrUnknown`** (`DisplayNameTest`) — extension function on a nullable
   `String?` receiver, with a sensible fallback.
2. **`wordCount`** (`WordCountTest`) — an extension property with an explicit getter,
   no backing field.
3. **`Point.get`** (`PointIndexingTest`) — an `operator` function declared as an
   extension, wiring up `point[i]` indexing.
4. **`Meters.parse`** (`MetersParseTest`) — an extension on a companion object, so it
   reads like a static factory call.
5. **`kindsOf`** (`KindsOfTest`) — extensions resolve on the declared type of a
   reference, not its runtime type, unlike a real virtual member.
6. **`greetTwice`** (`GreetTwiceTest`) — a member function always wins over an
   extension declared with the same signature.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
