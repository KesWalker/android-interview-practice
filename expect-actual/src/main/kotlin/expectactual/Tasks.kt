package expectactual

/**
 * ExpectActual practice.
 *
 * A single JVM module can't host real `expect`/`actual` source sets, so
 * this models the PATTERN instead: PlatformStore is the "expect" contract,
 * AndroidStore and IosStore are two "actual" implementations of it, and the
 * tasks below are the shared logic, the factory that resolves an actual per
 * platform, and the rules that keep shared code honestly platform-agnostic.
 *
 * Each function below is broken or unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to
 * implement it so its test goes GREEN. Run a single test class from the
 * gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :expect-actual:test
 */

/** The common "expect" contract: everything shared code may use to persist small values. */
interface PlatformStore {
    fun read(key: String): String?
    fun write(key: String, value: String)
    fun delete(key: String)
}

/** One "actual", standing in for a SharedPreferences-backed store on Android. */
class AndroidStore(private val backing: MutableMap<String, String> = mutableMapOf()) : PlatformStore {
    override fun read(key: String): String? = backing[key]
    override fun write(key: String, value: String) { backing[key] = value }
    override fun delete(key: String) { backing.remove(key) }
}

/** The other "actual", standing in for an NSUserDefaults-backed store on iOS. */
class IosStore(private val backing: MutableMap<String, String> = mutableMapOf()) : PlatformStore {
    override fun read(key: String): String? = backing[key]
    override fun write(key: String, value: String) { backing[key] = value }
    override fun delete(key: String) { backing.remove(key) }
}

/** Which platform-specific "actual" to resolve at startup. */
enum class Platform { ANDROID, IOS }

// TODO(t1): T1BumpLaunchCountTest
// Read the "launchCount" key from `store` as an Int (0 if it's absent),
// increment it by one, write the new value back to `store` as a String, and
// return the new count. This is written only against the PlatformStore
// interface, so it must behave identically no matter which "actual" is
// injected.
fun bumpLaunchCount(store: PlatformStore): Int {
    TODO("t1: read launchCount as Int defaulting to 0, increment, write back, return it")
}

// TODO(t2): T2MigrateLegacyKeyTest
// Shared migration logic: if `oldKey` is present in `store`, copy its value
// to `newKey` and delete `oldKey`, then return the value now at `newKey`
// (whether it was just migrated or was already there). If `oldKey` is
// absent and `newKey` is also absent, return null.
fun migrateLegacyKey(store: PlatformStore, oldKey: String, newKey: String): String? {
    TODO("t2: move oldKey's value to newKey if present, else fall back to whatever's already at newKey")
}

// TODO(t3): T3ProvidePlatformStoreTest
// The factory that resolves the right "actual" for a given platform token,
// the runtime stand-in for what the Kotlin compiler does at compile time
// when it links an expect declaration to its actual.
fun providePlatformStore(platform: Platform): PlatformStore {
    TODO("t3: return AndroidStore() for ANDROID, IosStore() for IOS")
}

/** Import prefixes that have no business appearing in commonMain: they only exist on one platform. */
val PLATFORM_ONLY_PREFIXES = listOf(
    "android.",
    "androidx.",
    "java.",
    "javax.",
    "platform.",
    "kotlinx.cinterop",
    "UIKit."
)

// TODO(t4): T4FindPlatformLeaksTest
// Given the list of imports a "commonMain" file actually uses, return the
// subset that start with any prefix in PLATFORM_ONLY_PREFIXES, in the order
// they appear. An empty result means the file is safe to keep in
// commonMain.
fun findPlatformLeaks(imports: List<String>): List<String> {
    TODO("t4: filter imports down to the ones starting with a platform-only prefix")
}

/** Thrown when a "commonMain" file references a platform-only import. */
class PlatformLeakException(message: String) : RuntimeException(message)

// TODO(t5): T5RequireCommonMainSafeTest
// Use findPlatformLeaks to validate `imports`. If any leaks are found,
// throw PlatformLeakException with a message that includes every offending
// import, comma-separated. Otherwise do nothing.
fun requireCommonMainSafe(imports: List<String>) {
    TODO("t5: throw PlatformLeakException listing the leaked imports, comma-separated, if findPlatformLeaks finds any")
}

/** A concern a KMP module might need to own. */
enum class Responsibility {
    VALIDATE_EMAIL_FORMAT,
    COMPUTE_ORDER_TOTAL,
    PARSE_JSON_RESPONSE,
    RETRY_WITH_BACKOFF,
    RENDER_NATIVE_BUTTON,
    ACCESS_CAMERA_HARDWARE,
    SHOW_PLATFORM_TOAST,
    READ_FILE_FROM_DISK
}

/** Where a responsibility's code is allowed to live. */
enum class SourceSet { COMMON_MAIN, PLATFORM_MAIN }

// TODO(t6): T6ClassifyResponsibilityTest
// Decide whether a responsibility belongs in commonMain (pure logic that
// touches no platform API) or a platform source set (anything that touches
// a real platform surface: UI widgets, hardware, or the filesystem).
fun classifyResponsibility(responsibility: Responsibility): SourceSet {
    TODO("t6: COMMON_MAIN for pure logic, PLATFORM_MAIN for anything touching a platform API")
}
