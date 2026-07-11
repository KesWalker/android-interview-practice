# MVI: reducer & state machine

Practice module for the **MVI Pattern** topic on Android Interview Prep.

You're wiring up the MVI plumbing for two small screens: a counter and a user
profile. Each task is a small piece that's currently unwritten, with a
matching test that's **red**. Make each test **green**, one at a time, and
you'll have written the core MVI idioms the way they come up in interviews.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter
next to a test class, or from a terminal:

```bash
./gradlew :mvi:test                                 # run everything
./gradlew :mvi:test --tests "*T1ReduceCounterTest"     # one task
```

## The tasks

All the work is in `src/main/kotlin/mvi/Tasks.kt`.

1. **`reduceCounter`** (`T1ReduceCounterTest`) — given the current state and an
   intent, compute and return the next state.
2. **`CounterStore.dispatch`** (`T2CounterStoreTest`) — the one entry point every
   intent goes through to produce and publish the next state.
3. **`loadUser`** (`T3LoadUserTest`) — run an async fetch outside the reducer,
   then fold its outcome back in as a new intent.
4. **`CartEffects.addToCart`** (`T4CartEffectsTest`) — announce a one-off effect
   through a dedicated channel instead of state.
5. **`reduceForm`** (`T5ReduceFormTest`) — a reducer that stays pure by taking its
   timestamp from the intent instead of the wall clock.
6. **`loadProfile`** (`T6ProfileStoreTest`) — fold two independent async results into
   one state via a single intent.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk
you through each one and tick them off as your tests go green.
