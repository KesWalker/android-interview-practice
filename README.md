# Android Interview Practice

Hands-on companion to [Android Interview Prep](https://android-interview-prep.web.app).
Each folder is a self-contained practice module for one topic: a small,
test-driven exercise you work through with the site's voice **Pair** tutor, which
watches your screen and ticks off each task as its test goes green.

## Getting started

```bash
git clone https://github.com/KesWalker/android-interview-practice.git
cd android-interview-practice
```

Open the folder in Android Studio (or IntelliJ) — one import covers every topic.
Then pick a topic module and start pairing:

```bash
./gradlew :null-safety:test    # run a topic's tests
```

Each module is independent: you can do them in any order, and each has its own
`README.md` with the scenario and task list.

## Topics

| Module | Topic | What you practise |
| --- | --- | --- |
| [`null-safety/`](null-safety) | Null Safety | `?.`, `?:`, `mapNotNull`, `as?` — no `!!` |

More topics are added as their modules land. Language topics are plain Kotlin/JVM
modules (fast, no emulator); Android-specific topics ship as Android modules.

## How the pairing works

On the site, open a topic's **Pair** tab, read the scenario, then **Start pair
programming**. Share your screen; the tutor coaches you task by task and ticks each
one off once it can see the test pass. Finish them all and the topic earns a
**Pair ✓**.
