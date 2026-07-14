# SecureStore

Practice module for the **Security: Data at Rest** topic on Android Interview
Prep.

You're building the decision logic behind where a piece of data is allowed to
live: classifying a field by sensitivity, routing it to plain prefs versus an
encrypted store versus a keystore-backed store, a keystore-alike whose key
never leaves the store, a biometric-and-timeout gate on secret access, a
scoped-storage-alike path check that rejects traversal outside the app's own
directory, and a redaction pass that scrubs secrets before they hit a log.
Each task is a small function that's currently broken or unwritten, with a
matching test that's **red**. Make each test **green**, one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :secure-store:test                             # run everything
./gradlew :secure-store:test --tests "*T1ClassifyTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/securestore/Tasks.kt`.

1. **`classify`** (`T1ClassifyTest`) - sort a field kind into PUBLIC, PERSONAL, or SECRET.
2. **`storageLocationFor`** (`T2StorageLocationTest`) - route a sensitivity class to plain prefs, an encrypted store, or a keystore-backed store.
3. **`KeyStoreAlike.encrypt` / `.decrypt`** (`T3KeyStoreAlikeTest`) - a keystore-alike where the key never leaves the store, only encrypt/decrypt by alias.
4. **`decryptSecret`** (`T4DecryptSecretTest`) - require a recent-enough biometric auth before a SECRET-class item decrypts, and let it expire.
5. **`resolveWithinAppStorage`** (`T5ResolveWithinAppStorageTest`) - a scoped-storage-alike path check that rejects traversal outside the app's own directory.
6. **`redact`** (`T6RedactTest`) - scrub secrets out of a log line before it's ever written.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
