# Test Doubles

Practice module for the **Test doubles: fakes, stubs, spies (hand-rolled)** topic on
Android Interview Prep.

You're building the pieces of a tiny signup flow, one hand-rolled test double at a
time, then wiring them together to test a real service. Each task is a small type
that's currently broken or unwritten, with a matching test that's **red**. Make each
test **green**, one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :test-doubles:test                          # run everything
./gradlew :test-doubles:test --tests "*SignupServiceTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/doubles/Tasks.kt`.

1. **`InMemoryUserStore`** (`InMemoryUserStoreTest`) — a working in-memory
   `UserStore` that actually remembers saved users and reports on them.
2. **`StubEmailSender`** (`StubEmailSenderTest`) — an `EmailSender` that always
   returns the same preconfigured result, whatever it's asked to send.
3. **`SpyEmailSender`** (`SpyEmailSenderTest`) — an `EmailSender` that records
   every call it receives, then forwards it to a wrapped sender.
4. **`SignupService`** (`SignupServiceTest`) — a service that registers a new
   email, skips duplicates, and reports whether the welcome email went out,
   tested by wiring the fake and spy from tasks 1-3 together.
5. **`ScriptedEmailSender`** (`ScriptedEmailSenderTest`) — a hand-rolled
   strict mock: an `EmailSender` that must be explicitly stubbed before use,
   and fails loudly on an unstubbed call instead of returning a default.
6. **`validateSignupEmail`** (`ValidateSignupEmailTest`) — reject an obviously
   invalid email before it reaches the store or the email sender.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
