# InputValidation

Practice module for the **Security: Integrity** topic on Android Interview
Prep.

You're building the defensive checks that keep untrusted input from becoming
an attack: a parameterized query builder that binds arguments instead of
splicing them into SQL, a detector that flags the concatenation smell, an
allow-list validator that rejects bad input outright rather than trying to
clean it up, a WebView-alike policy that separates "may this URL load" from
"does this URL get the JS bridge," and integrity-verdict parsing that ends
with the one rule that matters most: never trust a verdict that wasn't
verified server-side. Each task is a small function that's currently broken
or unwritten, with a matching test that's **red**. Make each test **green**,
one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :input-validation:test                          # run everything
./gradlew :input-validation:test --tests "*T1BuildQueryTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/validation/Tasks.kt`.

1. **`buildQuery`** (`T1BuildQueryTest`) - bind an untrusted value as a placeholder argument instead of splicing it into SQL text.
2. **`hasInjectionSmell`** (`T2HasInjectionSmellTest`) - flag a query string built by concatenating untrusted input.
3. **`validateUsername`** (`T3ValidateUsernameTest`) - an allow-list validator that rejects bad input outright, never sanitizes it.
4. **`evaluateUrlLoad`** (`T4EvaluateUrlLoadTest`) - decide whether a WebView-alike may load a URL, and separately, whether it gets the JS bridge.
5. **`parseVerdict`** (`T5ParseVerdictTest`) - turn a raw Play-Integrity-style verdict into a trust decision.
6. **`enforceServerVerified`** (`T6EnforceServerVerifiedTest`) - never act on a verdict that wasn't verified server-side, no matter what it claims.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
