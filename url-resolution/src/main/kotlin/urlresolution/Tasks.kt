package urlresolution

import java.net.URLEncoder

/**
 * Retrofit-style URL resolution practice.
 *
 * Each function below is broken or unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to
 * implement it so its test goes GREEN. Run a single test class from the
 * gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :url-resolution:test
 */

private fun encodeComponent(value: String): String =
    URLEncoder.encode(value, "UTF-8").replace("+", "%20")

// TODO(t1): T1ResolveUrlTest
// Resolve `path` against `baseUrl` the way Retrofit resolves an @GET path
// against its base URL. Three cases: if `path` is itself a full URL (starts
// with "http://" or "https://"), it replaces the base URL entirely. If
// `path` starts with "/", it replaces the base URL's whole path, keeping
// only the base URL's scheme and host. Otherwise `path` is appended after
// the base URL's existing path.
fun resolveUrl(baseUrl: String, path: String): String {
    TODO("t1: full URL replaces everything, a leading slash replaces baseUrl's path, otherwise append")
}

// TODO(t2): T2SubstitutePathParamsTest
// Replace every "{name}" placeholder in `template` with the URL-encoded
// value of `params[name]`. Throw IllegalArgumentException if a placeholder
// has no matching entry in `params`.
fun substitutePathParams(template: String, params: Map<String, String>): String {
    TODO("t2: replace {name} placeholders with encoded param values, throw on a missing one")
}

// TODO(t3): T3BuildQueryStringTest
// Build a query string like "?a=1&b=2" from `params`, encoding keys and
// values and skipping any entry whose value is null. Return an empty string
// (no leading "?") when there are no non-null entries.
fun buildQueryString(params: Map<String, String?>): String {
    TODO("t3: encode non-null params into a leading-? query string, or empty string if none")
}

// TODO(t4): T4BuildRequestUrlTest
// Assemble the full request URL the way Retrofit does for one call: fill in
// `pathTemplate`'s params, resolve the result against `baseUrl`, then
// append the query string built from `queryParams`.
fun buildRequestUrl(
    baseUrl: String,
    pathTemplate: String,
    pathParams: Map<String, String> = emptyMap(),
    queryParams: Map<String, String?> = emptyMap()
): String {
    TODO("t4: substitute path params, resolve against baseUrl, then append the query string")
}

/** What actually happened when a request was attempted. */
sealed interface RequestOutcome
data class HttpResponse(val statusCode: Int) : RequestOutcome
data class IoFailure(val cause: Throwable) : RequestOutcome

/** The result an API call is mapped to for callers to handle. */
sealed interface ApiResult<out T>
data class Success<T>(val data: T) : ApiResult<T>
data class HttpException(val code: Int) : ApiResult<Nothing>
data class NetworkError(val cause: Throwable) : ApiResult<Nothing>

// TODO(t5): T5MapOutcomeTest
// Map a raw request outcome to an ApiResult: a 2xx status becomes
// Success(data), any other status becomes HttpException carrying the code,
// and an IoFailure becomes NetworkError carrying the cause.
fun <T> toApiResult(outcome: RequestOutcome, data: T): ApiResult<T> {
    TODO("t5: 2xx -> Success, other status -> HttpException, IoFailure -> NetworkError")
}

// TODO(t6): T6IsRetryableTest
// Decide whether a failed result is worth retrying: a NetworkError (a
// timeout or other IO failure) is retryable, an HttpException is retryable
// only for 429 (rate limited) or a 5xx server error, never for any other
// 4xx status, and a Success is never retryable.
fun isRetryable(result: ApiResult<*>): Boolean {
    TODO("t6: retry NetworkError and 429/5xx, never a permanent 4xx or a Success")
}
