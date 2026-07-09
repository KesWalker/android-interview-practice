# Interceptor Chains

Practice module for the **OkHttp & Interceptors** topic on Android Interview Prep.

You're building a tiny stand-in for OkHttp's request/response pipeline, modelled
entirely in plain Kotlin: a `Request` goes in, a list of interceptors each get a
turn, and a `Response` comes back out. Each task is a small function that's
currently broken or unwritten, with a matching test that's **red**. Make each test
**green**, one at a time, and you'll have written the idioms that come up when an
interviewer asks how OkHttp's interceptor pipeline actually works.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :interceptors:test                              # run everything
./gradlew :interceptors:test --tests "*ChainExecutionTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/interceptors/Tasks.kt`. Work out each idiom
yourself, or pair with the tutor and let it nudge you toward it.

1. **`executeChain`** (`ChainExecutionTest`) — run a list of interceptors in order,
   ending in a network call, respecting an interceptor that stops early.
2. **`authInterceptor`** (`AuthInterceptorTest`) — attach a bearer token header to
   every request before continuing.
3. **`cachingInterceptor`** (`CachingInterceptorTest`) — serve a stored response
   without continuing, or continue and store the result for next time.
4. **`retryOnUnauthorized`** (`RetryOnUnauthorizedTest`) — refresh the token and
   retry once when the response comes back unauthorized.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
