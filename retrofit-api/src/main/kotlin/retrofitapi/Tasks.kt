package retrofitapi

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Response
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit practice.
 *
 * This module talks to a real Retrofit + OkHttp stack, tested against a real
 * (local) server via MockWebServer. Each function below is unwritten and has
 * a matching test in src/test that is currently RED. Your job, one task at a
 * time, is to implement it so its test goes GREEN. Run a single test class
 * from the gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :retrofit-api:test
 */

@Serializable
data class User(val id: Int, val name: String, val active: Boolean = true)

interface UserApi {
    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: Int, @Query("verbose") verbose: Boolean = false): User

    @GET("users/{id}")
    suspend fun getUserResponse(@Path("id") id: Int): retrofit2.Response<User>
}

// TODO(t1): T1CreateUserApiTest
// Build a real Retrofit client pointed at `baseUrl`: attach a kotlinx.serialization
// JSON converter and create() a UserApi from it.
fun createUserApi(baseUrl: String): UserApi {
    TODO("t1: Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(<json converter>).build().create(UserApi::class.java)")
}

interface LegacyApi {
    @GET("/v2/users/{id}")
    suspend fun getUser(@Path("id") id: Int): User
}

// TODO(t2): T2LeadingSlashTest
// Build a client for LegacyApi the same way as createUserApi, but `baseUrl`
// might arrive without a trailing slash, and Retrofit.Builder.baseUrl throws
// IllegalArgumentException if it doesn't end in one. Normalize it first.
// (Separately: LegacyApi's @GET path starts with "/", which makes Retrofit
// treat it as an absolute path and drop any path segment already present in
// baseUrl. The tests exercise that behaviour too, nothing extra to write for it.)
fun createLegacyApi(baseUrl: String): LegacyApi {
    TODO("t2: append a trailing slash to baseUrl if it's missing one, then build the client")
}

@Serializable
data class NewUserRequest(val name: String, val email: String)

interface CreateUserApi {
    @POST("users")
    suspend fun createUser(@Body request: NewUserRequest): User
}

// TODO(t3): T3CreateUserBodyTest
// Same client-building shape again, this time for CreateUserApi, whose
// suspend fun POSTs a @Serializable body.
fun createCreateUserApi(baseUrl: String): CreateUserApi {
    TODO("t3: build a Retrofit client for CreateUserApi with the JSON converter")
}

/** Where fetchUserResult landed. */
sealed interface UserResult {
    data class Found(val user: User) : UserResult
    data class NotFound(val id: Int) : UserResult
    data class Failed(val code: Int) : UserResult
}

// TODO(t4): T4TypedErrorTest
// Call api.getUser(id) (the bare-User suspend fun, which throws HttpException
// on any non-2xx response instead of returning). Catch HttpException: a 404
// becomes NotFound, any other code becomes Failed with that code. A
// successful call becomes Found. (api.getUserResponse never throws for HTTP
// errors at all, it just comes back with isSuccessful == false: the tests
// exercise that contrast too.)
suspend fun fetchUserResult(api: UserApi, id: Int): UserResult {
    TODO("t4: try { Found(api.getUser(id)) } catch (e: HttpException) { map e.code() to NotFound or Failed }")
}

// TODO(t5): T5AuthInterceptorTest
// Build a real OkHttp Interceptor that attaches "Authorization: Bearer
// <token>" to every outgoing request, calling tokenProvider() fresh for
// each one, then continues the chain.
fun authHeaderInterceptor(tokenProvider: () -> String): Interceptor {
    TODO("t5: chain -> chain.proceed(chain.request().newBuilder().header(\"Authorization\", \"Bearer \${tokenProvider()}\").build())")
}

private fun priorResponseCount(response: Response): Int {
    var count = 1
    var prior = response.priorResponse
    while (prior != null) {
        count++
        prior = prior.priorResponse
    }
    return count
}

// TODO(t6): T6RefreshAuthenticatorTest
// Build a real OkHttp Authenticator: on a 401, call refreshToken() and retry
// the request once with the refreshed header. If it's already been retried
// once (use priorResponseCount above to tell), give up by returning null so
// OkHttp doesn't loop forever.
fun refreshingAuthenticator(refreshToken: () -> String): Authenticator {
    TODO("t6: return null once priorResponseCount(response) >= 2, otherwise rebuild the request with a refreshed Authorization header")
}
