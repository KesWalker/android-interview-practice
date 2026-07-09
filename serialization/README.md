# JSON Serialization

Practice module for the **JSON Serialization** topic on Android Interview Prep.

You're wiring up `kotlinx.serialization` for a small set of API models. Each task
is a small function that's currently broken or unwritten, with a matching test
that's **red**. Make each test **green**, one at a time, and you'll have written
the idiomatic JSON handling the way it comes up in interviews.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :serialization:test                              # run everything
./gradlew :serialization:test --tests "*EncodeCardTest"     # one task
```

## The tasks

All the work is in `src/main/kotlin/serialization/Tasks.kt`.

1. **`encodeCard`** (`EncodeCardTest`) — turn a `Card` into its JSON text.
2. **`decodeLenientProfile`** (`DecodeLenientProfileTest`) — parse profile JSON that may carry fields the model doesn't declare.
3. **`decodePreferences`** (`DecodePreferencesTest`) — parse preferences JSON that might leave some fields out.
4. **`decodeShapes`** (`DecodeShapesTest`) — parse a JSON array into the right shape subtype per element.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
