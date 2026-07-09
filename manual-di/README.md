# Manual Dependency Injection

Practice module for the **Dagger Fundamentals & Manual DI** topic on Android Interview
Prep.

You're wiring a tiny user-profile feature by hand, no framework, the way the Android
docs' "manual dependency injection" guide describes and the way Dagger/Hilt generate
code for you under the hood. Each task is a small piece of that container that's
currently broken or unwritten, with a matching test that's **red**. Make each test
**green**, one at a time, and you'll have hand-rolled the same graph-building,
scoping, and resolution logic a real DI framework does for you.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :manual-di:test                              # run everything
./gradlew :manual-di:test --tests "*ManualWiringTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/di/Tasks.kt`.

1. **`buildUserService`** (`ManualWiringTest`) — wire `Logger`, `UserRepository`, and
   `UserService` together by hand, constructor injection all the way, without
   duplicating any dependency.
2. **`SingletonProvider`** (`SingletonScopeTest`) — a provider that hands back the
   same instance on every call.
3. **`FactoryProvider`** (`FactoryScopeTest`) — a provider that hands back a new
   instance on every call.
4. **`Container`** (`ContainerResolutionTest`) — resolve a registered binding by key,
   and fail with a clear error when a binding is missing.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
