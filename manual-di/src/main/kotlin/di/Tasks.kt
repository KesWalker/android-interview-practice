package di

/**
 * Manual Dependency Injection practice.
 *
 * Each class/function below is broken or unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to fill in
 * the wiring so its test goes GREEN. Run a single test class from the gutter
 * in Android Studio, or run them all with:
 *
 *     ./gradlew :manual-di:test
 */

// A leaf dependency other classes are wired with.
class Logger {
    val logs = mutableListOf<String>()
    fun log(message: String) {
        logs.add(message)
    }
}

// A dependency that needs a Logger to do its job.
class UserRepository(val logger: Logger) {
    fun findUser(id: Int): String {
        logger.log("find $id")
        return "user-$id"
    }
}

// The top of the graph: needs a repository and, separately, a logger.
class UserService(val repository: UserRepository, val logger: Logger) {
    fun greet(id: Int): String {
        val name = repository.findUser(id)
        logger.log("greet $name")
        return "Hello, $name"
    }
}

// TODO(t1): T1ManualWiringTest
// Build a fully-wired UserService, with the whole graph sharing one Logger instance.
fun buildUserService(): UserService {
    TODO("t1: construct Logger, UserRepository, and UserService, sharing a single Logger instance across the graph")
}

// TODO(t2): T2SingletonScopeTest
// A provider that hands back the same instance on every call.
class SingletonProvider<T>(private val factory: () -> T) {
    fun get(): T {
        TODO("t2: return the same instance every time, creating it only on the first call")
    }
}

// TODO(t3): T3FactoryScopeTest
// A provider that hands back a new instance on every call.
class FactoryProvider<T>(private val factory: () -> T) {
    fun get(): T {
        TODO("t3: return a fresh instance from the factory every time")
    }
}

// TODO(t4): T4ContainerResolutionTest
// A tiny container mapping string keys to providers.
class Container {
    private val providers = mutableMapOf<String, () -> Any>()

    fun register(key: String, provider: () -> Any) {
        providers[key] = provider
    }

    fun resolve(key: String): Any {
        TODO("t4: return the value from the registered provider for key, or throw IllegalStateException naming the missing key")
    }
}

// TODO(t5): T5FlowScopeTest
// A scope layered on top of the Container idea: resolving the same key twice
// within one FlowScope returns the same cached instance, a different FlowScope
// never shares that cache, and release() clears everything so a later resolve()
// with the same key builds fresh.
class FlowScope {
    private val cache = mutableMapOf<String, Any>()

    fun resolve(key: String, factory: () -> Any): Any {
        TODO("t5: return the cached instance for key, creating it from factory on first resolve")
    }

    fun release() {
        TODO("t5: clear the cache so a later resolve builds a fresh instance")
    }
}

// A session that needs both a graph dependency and a call-site value.
class UserSession(val userId: Int, val logger: Logger)

// TODO(t6): T6UserSessionFactoryTest
// The factory is built once with its graph dependency (a Logger), but
// create(userId) needs a userId that's only known at the call site -- exactly
// what @AssistedInject solves in real Dagger. Wire it to build a UserSession
// from both.
class UserSessionFactory(private val logger: Logger) {
    fun create(userId: Int): UserSession {
        TODO("t6: build a UserSession from the call-site userId and the injected Logger")
    }
}
