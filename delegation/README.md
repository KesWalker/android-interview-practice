# Delegation

Practice module for the **Delegation** topic on Android Interview Prep.

You're building small pieces of a notifications/config/game backend, and each
piece needs to hand its behavior off to something else instead of
reimplementing it: an interface forwarded to another object, a property whose
read/write logic lives somewhere else. Each task is a small function that's
currently unwritten, with a matching test that's **red**. Make each test
**green**, one at a time, and you'll have written the `by`-keyword idioms the
way they come up in interviews.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :delegation:test                          # run everything
./gradlew :delegation:test --tests "*LoudNotifierTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/delegation/Tasks.kt`. Work out the idiom
yourself, or pair with the tutor and let it nudge you toward it.

1. **`loudNotifier`** (`LoudNotifierTest`) — a `Notifier` that forwards every
   member to another `Notifier`, except one method it changes.
2. **`nonNegative`** (`MeterTest`) — a property delegate whose writes are
   clamped so the stored value never goes below zero.
3. **`cachedConfig`** (`ConfigCacheTest`) — a value that's computed once, on
   first read, then reused on every later read.
4. **`onlyIncreasing`** (`GameTest`) — a property delegate that silently
   rejects any write that isn't an improvement on the current value.
5. **`withDisplayLabel`** (`WithDisplayLabelTest`) — the classic delegate
   gotcha: a `by`-delegated interface's own methods can't see overrides made
   on the deriving class.
6. **`auditedSetting`** (`AuditedSettingTest`) — the standard `Delegates.observable`
   delegate, which notifies after every write but can never reject one.
7. **`selfNamingProperty`** (`SelfNamingPropertyTest`) — a custom delegate that
   implements `provideDelegate` to capture the property's own declared name
   once, at bind time.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
