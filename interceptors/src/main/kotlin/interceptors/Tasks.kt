package interceptors

/**
 * Interceptor Chains practice.
 *
 * A tiny stand-in for OkHttp's request/response pipeline, modelled entirely in
 * plain Kotlin: a `Request` goes in, interceptors get a turn in order, and a
 * `Response` comes back out. Each function below is unwritten and has a
 * matching test in src/test that is currently RED. Your job, one task at a
 * time, is to implement it idiomatically so its test goes GREEN. Run a single
 * test class from the gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :interceptors:test
 */

data class Request(val path: String, val headers: Map<String, String> = emptyMap())

data class Response(val code: Int, val body: String)

fun interface Interceptor {
    fun intercept(chain: Chain): Response
}

interface Chain {
    val request: Request
    fun proceed(request: Request): Response
}

// TODO(t1): ChainExecutionTest
// Run `interceptors` in order against `request`, letting each one either
// continue on to the next one (and eventually to `network`) or stop early
// with its own response without the rest of the list or `network` running.
fun executeChain(interceptors: List<Interceptor>, request: Request, network: (Request) -> Response): Response {
    TODO("t1: run each interceptor in order, ending with network, respecting early returns")
}

// TODO(t2): AuthInterceptorTest
// Build an interceptor that attaches a bearer token header (from
// `tokenProvider`) to the request before continuing.
fun authInterceptor(tokenProvider: () -> String): Interceptor {
    TODO("t2: add an Authorization header built from tokenProvider() to the request, then continue")
}

// TODO(t3): CachingInterceptorTest
// Build an interceptor that serves a stored response for a request's path
// without continuing, or continues and stores whatever comes back for next
// time.
fun cachingInterceptor(cache: MutableMap<String, Response>): Interceptor {
    TODO("t3: return the cached response for this path if present, else continue and cache the result")
}

// TODO(t4): RetryOnUnauthorizedTest
// Build an interceptor that, when the response comes back unauthorized,
// refreshes the token and tries the request one more time.
fun retryOnUnauthorized(refreshToken: () -> String): Interceptor {
    TODO("t4: on a 401 response, rebuild the request with a refreshed token and continue once more")
}
