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
./gradlew :serialization:test --tests "*T1EncodeCardTest"     # one task
```

## The tasks

All the work is in `src/main/kotlin/serialization/Tasks.kt`.

1. **`encodeCard`** (`T1EncodeCardTest`) — turn a `Card` into its JSON text.
2. **`decodeLenientProfile`** (`T2DecodeLenientProfileTest`) — parse profile JSON that may carry fields the model doesn't declare.
3. **`decodePreferences`** (`T3DecodePreferencesTest`) — parse preferences JSON that might leave some fields out.
4. **`decodeShapes`** (`T4DecodeShapesTest`) — parse a JSON array into the right shape subtype per element.
5. **`decodeAccount`** (`T5DecodeAccountTest`) — parse account JSON into a model whose property has a renamed canonical JSON key, while still accepting older key names some clients may still send.
6. **`encodeSession`** (`T6EncodeSessionTest`) — encode a model so a field never appears in the JSON at all.
7. **`decodeSettingsLenient`** (`T7DecodeSettingsLenientTest`) — parse settings JSON that may send an explicit null for a non-nullable field with a default, falling back to that default instead of throwing.
8. **`CentsSerializer`** (`T8CentsSerializerTest`) — write a custom `KSerializer` for a value class so it reads and writes as a plain decimal string instead of the default numeric representation.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
