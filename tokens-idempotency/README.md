# Auth Tokens & Idempotency

Practice module for the **Auth Tokens & Idempotency** topic on Android Interview Prep.

You're building the pieces of a token manager: a store that makes retried payment
calls idempotent, a token cache that serves twenty concurrent callers off a single
refresh, a version of that refresh that survives one caller being cancelled, and a
cache that respects expiry without corrupting itself when a refresh fails. Each task
is a small class that's currently unwritten, with a matching test that's **red**.
Make each test **green**, one at a time, and you'll have written the idioms that
come up in almost every fintech live-coding round.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :tokens-idempotency:test                              # run everything
./gradlew :tokens-idempotency:test --tests "*T1IdempotencyStoreTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/tokens/Tasks.kt`.

1. **`IdempotencyStore`** (`T1IdempotencyStoreTest`) — run an operation once per key; a
   repeat call with the same key returns the stored result instead of re-running it.
2. **`SingleFlightTokenManager`** (`T2SingleFlightTokenManagerTest`) — serve a cached
   token, and when it's missing, make sure many concurrent callers still trigger
   only one refresh.
3. **`SharedRefreshTokenManager`** (`T3SharedRefreshTokenManagerTest`) — share one
   in-flight refresh across every waiting caller so that cancelling one caller can't
   take the refresh down with it.
4. **`ExpiringTokenCache`** (`T4ExpiringTokenCacheTest`) — cache a token until it
   expires, and make sure a failed refresh propagates without corrupting what was
   cached before.
5. **`RotatingRefreshTokenStore`** (`T5RotatingRefreshTokenStoreTest`) — rotate the
   refresh token on every successful refresh, and revoke the whole token family
   if a stale, already-rotated token is ever replayed.
6. **`attemptRefresh`** (`T6AttemptRefreshTest`) — treat a 401 on the refresh call
   itself as a clean session expiry instead of retrying, while letting any other
   failure propagate.
7. **`redactForLogging`** (`T7RedactForLoggingTest`) — mask the Authorization
   header's value before request/response data ever reaches a debug log line.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
