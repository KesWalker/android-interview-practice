# Clean Architecture & Use Cases

Practice module for the **Clean Architecture & Use Cases** topic on Android Interview Prep.

You're building the domain layer of a small news app: pure Kotlin, no Android
imports, wired together with fake repositories in the tests. Each task is a
small use-case class that's currently broken or unwritten, with a matching
test that's **red**. Make each test **green**, one at a time, and you'll have
written the domain-layer idioms that come up in interviews.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :clean-arch:test                       # run everything
./gradlew :clean-arch:test --tests "*T1GetUserUseCaseTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/clean/Tasks.kt`.

1. **`GetUserUseCase`** (`T1GetUserUseCaseTest`) — look up a user by id, or null when they don't exist.
2. **`GetArticlesWithAuthorsUseCase`** (`T2GetArticlesWithAuthorsUseCaseTest`) — pair each article with its author's name, dropping any article whose author can't be found.
3. **`GetUserFeedUseCase`** (`T3GetUserFeedUseCaseTest`) — build a user's feed from their profile and the article list, or null if the user can't be found.
4. **`CalculateTotalUseCase`** (`T4CalculateTotalUseCaseTest`) — multiply price by quantity, running the work on the dispatcher it's given rather than whatever context called it.
5. **`GetUserNewsUseCase`** (`T5GetUserNewsUseCaseTest`) — compose two other use cases instead of hitting repositories directly.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
