# Certificate Pinning

Practice module for the **Network Security** topic on Android Interview Prep.

You're building the decision logic behind certificate pinning and Android's
network security config: validating a leaf-to-root cert chain, checking a
pin set against that chain, spotting a pin configuration that's fragile
because it only pins the leaf, rejecting a pin set with no backup pin, and
applying cleartext and debug-CA rules from a network security config. Each
task is a small function that's currently broken or unwritten, with a
matching test that's **red**. Make each test **green**, one at a time.
Hashes are modelled as opaque strings, there's no real crypto involved.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run
gutter next to a test class, or from a terminal:

```bash
./gradlew :cert-pinning:test                                # run everything
./gradlew :cert-pinning:test --tests "*T3IsFragilePinningTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/pinning/Tasks.kt`.

1. **`validateChain`** (`T1ValidateChainTest`) - check that every cert in a
   chain is signed by the next one, the root is self-signed, and nothing has
   expired at a given time.
2. **`pinsMatch`** (`T2PinsMatchTest`) - a pin set matches if any cert
   anywhere in the chain matches any pin.
3. **`isFragilePinning`** (`T3IsFragilePinningTest`) - flag a pin config
   that only matches the leaf, the fragile setup that breaks on the next
   cert rotation.
4. **`ensureBackupPin`** (`T4EnsureBackupPinTest`) - reject a pin set with
   fewer than two pins, a single pin bricks the app when its cert rotates.
5. **`isCleartextPermitted`** (`T5IsCleartextPermittedTest`) - apply a
   network security config's cleartext default with per-domain overrides.
6. **`isUserCaTrusted`** (`T6IsUserCaTrustedTest`) - make sure a
   debug-overrides user-CA trust setting only ever applies to debug builds.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk
you through each one and tick them off as your tests go green.
