# UrlResolution

Practice module for the **Retrofit / networking layer** topic on Android
Interview Prep.

You're building the URL and result-mapping logic Retrofit does under the
hood: resolving a relative path against a base URL (the classic gotcha,
where a leading slash quietly throws away the base URL's path), filling in
`{id}`-style path params with proper encoding, building a query string that
skips nulls, assembling all of that into one request URL, and mapping an
HTTP outcome to a sealed result your app can exhaustively handle, including
the retry policy an interviewer will actually ask about. Each task is a
small function that's currently broken or unwritten, with a matching test
that's **red**. Make each test **green**, one at a time.

## How to run it

Open the repo root in Android Studio (or IntelliJ) and use the green run gutter next
to a test class, or from a terminal:

```bash
./gradlew :url-resolution:test                          # run everything
./gradlew :url-resolution:test --tests "*T1ResolveUrlTest"   # one task
```

## The tasks

All the work is in `src/main/kotlin/urlresolution/Tasks.kt`.

1. **`resolveUrl`** (`T1ResolveUrlTest`) - resolve a relative path against a base URL: full URL replaces everything, a leading slash replaces the base URL's path, otherwise it appends.
2. **`substitutePathParams`** (`T2SubstitutePathParamsTest`) - fill in `{name}` placeholders with encoded values, throwing on a missing one.
3. **`buildQueryString`** (`T3BuildQueryStringTest`) - build an encoded `?a=1&b=2` query string, skipping null-valued params.
4. **`buildRequestUrl`** (`T4BuildRequestUrlTest`) - compose the three functions above into the URL Retrofit would actually send.
5. **`toApiResult`** (`T5MapOutcomeTest`) - map a raw request outcome to a sealed `ApiResult`: 2xx to `Success`, other statuses to `HttpException`, an IO failure to `NetworkError`.
6. **`isRetryable`** (`T6IsRetryableTest`) - decide whether a failed result is worth retrying: timeouts, 429 and 5xx are, a permanent 4xx never is.

Pair with the tutor on the site's **Pair** tab for this topic and it'll walk you
through each one and tick them off as your tests go green.
