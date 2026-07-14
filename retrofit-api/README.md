# Retrofit

Practice module for the **Retrofit & Networking** topic on Android Interview Prep.

This one is different from most of the other topic modules: instead of a fake
stand-in for the API, you're writing real `Retrofit`, `OkHttp` interfaces and
callbacks, and every test drives them against a real local server via
MockWebServer. If it compiles and the test goes green, it would work against
a real backend too. Each task is a small function or interface that's
currently broken or unwritten, with a matching test that's **red**. Make each
test **green**, one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :retrofit-api:test                       # run everything
./gradlew :retrofit-api:test --tests "*T1CreateUserApiTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/retrofitapi/Tasks.kt`.

1. **`createUserApi`** (`T1CreateUserApiTest`) - build a real Retrofit client for `UserApi`, whose `getUser` suspend fun uses `@GET`, `@Path` and `@Query`, and call it against MockWebServer.
2. **`createLegacyApi`** (`T2LeadingSlashTest`) - the leading-slash baseUrl gotcha: `Retrofit.Builder.baseUrl` requires a trailing slash, and a `@GET` path that starts with `/` resolves as absolute, dropping baseUrl's own path.
3. **`createCreateUserApi`** (`T3CreateUserBodyTest`) - a `@POST` with a `@Serializable` `@Body` model, verified by decoding the JSON MockWebServer actually received.
4. **`fetchUserResult`** (`T4TypedErrorTest`) - map a 404 to a typed result using `HttpException.code()`, and see the contrast with `Response<T>`, which never throws for HTTP errors.
5. **`authHeaderInterceptor`** (`T5AuthInterceptorTest`) - a real OkHttp `Interceptor` that attaches an auth header, confirmed by inspecting the header MockWebServer received.
6. **`refreshingAuthenticator`** (`T6RefreshAuthenticatorTest`) - a real OkHttp `Authenticator` that refreshes the token on a 401 and retries once, without looping forever.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
