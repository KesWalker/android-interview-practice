# Coroutines Basics

Practice module for the **Coroutines Basics** topic on Android Interview Prep.

You're building a tiny set of coroutine helpers that stand in for the kind of
async plumbing that shows up in real apps: a suspending network call, a
parallel fetch-and-combine, a dispatcher-aware runner, and a loop that
respects cancellation. Each task is a small function that's currently
unwritten, with a matching test that's **red**. Make each test **green**, one
at a time, and you'll have written the coroutine idioms that come up
constantly in interviews.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter
next to a test class, or from a terminal:

```bash
./gradlew :coroutines-basics:test                          # run everything
./gradlew :coroutines-basics:test --tests "*FetchGreetingTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/coroutines/Tasks.kt`. Work out the idiom
yourself, or pair with the tutor and let it nudge you toward it.

1. **`fetchGreeting`** (`FetchGreetingTest`) — suspend, simulate latency, return a greeting.
2. **`sumConcurrently`** (`SumConcurrentlyTest`) — run two suspend functions at the same time and combine their results.
3. **`runOn`** (`RunOnTest`) — run a block on a given dispatcher and return its result.
4. **`countTicks`** (`CountTicksTest`) — count ticks but stop early if the coroutine is cancelled.
5. **`fireAndForget`** (`FireAndForgetTest`) — launch a suspend block on a scope and hand back its Job without waiting.
6. **`blockingCall`** (`BlockingCallTest`) — bridge a suspend block into ordinary blocking code with runBlocking.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
