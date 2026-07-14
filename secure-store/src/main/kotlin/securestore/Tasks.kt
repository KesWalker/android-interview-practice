package securestore

/**
 * Data-at-rest storage practice.
 *
 * Each function below is broken or unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to
 * implement it so its test goes GREEN. Run a single test class from the
 * gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :secure-store:test
 */

/** The kind of field a screen is about to persist. */
enum class FieldKind {
    THEME_PREFERENCE,
    LANGUAGE_SETTING,
    DISPLAY_NAME,
    EMAIL_ADDRESS,
    PHONE_NUMBER,
    AUTH_TOKEN,
    ACCOUNT_PASSWORD,
    API_KEY
}

/** How sensitive a piece of data is, independent of where it ends up. */
enum class Sensitivity { PUBLIC, PERSONAL, SECRET }

// TODO(t1): T1ClassifyTest
// Classify a field kind by sensitivity: app-level preferences are PUBLIC,
// anything that identifies the user is PERSONAL, and credentials or tokens
// are SECRET.
fun classify(kind: FieldKind): Sensitivity {
    TODO("t1: map each FieldKind to PUBLIC, PERSONAL, or SECRET")
}

/** Where a value of a given sensitivity is allowed to live on disk. */
enum class StorageLocation { PLAIN_PREFS, ENCRYPTED_STORE, KEYSTORE_BACKED }

// TODO(t2): T2StorageLocationTest
// Pick the storage location for a sensitivity class: PUBLIC goes to plain
// prefs, PERSONAL goes to an encrypted store, and SECRET goes to a
// keystore-backed store. A secret must never end up in plain prefs.
fun storageLocationFor(sensitivity: Sensitivity): StorageLocation {
    TODO("t2: map PUBLIC/PERSONAL/SECRET to a storage location")
}

// TODO(t3): T3KeyStoreAlikeTest
// A keystore-alike: each alias gets its own key the first time it's used,
// and the key never leaves the store, there is no method that hands the key
// back out, only encrypt/decrypt by alias. Decrypting with an alias that has
// no key yet is an error.
class KeyStoreAlike {
    private val keys = mutableMapOf<String, Int>()

    fun encrypt(alias: String, plaintext: String): String {
        TODO("t3: generate or reuse a per-alias key and produce ciphertext that isn't the plaintext")
    }

    fun decrypt(alias: String, ciphertext: String): String {
        TODO("t3: reverse encrypt using alias's key; throw if the alias has no key")
    }
}

/** When a secret-class item was last unlocked by the user. */
data class AuthState(val lastAuthenticatedAtMillis: Long?)

/** Thrown when a SECRET-class item is requested without a fresh enough auth. */
class SecretAccessDeniedException(message: String) : RuntimeException(message)

// TODO(t4): T4DecryptSecretTest
// Gate decryption behind a biometric check, but only for SECRET-class data.
// PUBLIC and PERSONAL data decrypt freely. SECRET data needs a prior auth
// that happened within authTimeoutMillis of now, otherwise throw
// SecretAccessDeniedException instead of running decryptAction.
fun decryptSecret(
    sensitivity: Sensitivity,
    auth: AuthState,
    nowMillis: Long,
    authTimeoutMillis: Long,
    decryptAction: () -> String
): String {
    TODO("t4: require a recent-enough auth for SECRET data before calling decryptAction")
}

/** Thrown when a requested path would escape the app's own storage directory. */
class PathTraversalException(message: String) : RuntimeException(message)

// TODO(t5): T5ResolveWithinAppStorageTest
// Resolve requestedPath against appDir, treating requestedPath as always
// relative even if it looks absolute, and normalizing ".." segments. If the
// normalized result would land outside appDir, throw PathTraversalException
// instead of returning a path outside the app's own directory.
fun resolveWithinAppStorage(appDir: String, requestedPath: String): String {
    TODO("t5: normalize requestedPath under appDir, rejecting any escape via ..")
}

// TODO(t6): T6RedactTest
// Replace every occurrence of every non-empty entry in `secrets` inside
// `logLine` with the literal marker "[REDACTED]". Blank entries in `secrets`
// are ignored so they can't blank out the whole line.
fun redact(logLine: String, secrets: List<String>): String {
    TODO("t6: replace each non-empty secret's occurrences in logLine with [REDACTED]")
}
