package validation

/**
 * Injection defence and integrity practice.
 *
 * Each function below is broken or unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to
 * implement it so its test goes GREEN. Run a single test class from the
 * gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :input-validation:test
 */

/** A query split into its fixed SQL text and the values bound to its placeholders. */
data class BoundQuery(val sql: String, val args: List<String>)

// TODO(t1): T1BuildQueryTest
// Build a query that filters `table` on `whereColumn` equal to `value`.
// table and whereColumn are trusted schema names, but value is untrusted
// user input: bind it as a "?" placeholder argument instead of splicing it
// into the SQL text.
fun buildQuery(table: String, whereColumn: String, value: String): BoundQuery {
    TODO("t1: return sql with a ? placeholder for value, and value in args")
}

// TODO(t2): T2HasInjectionSmellTest
// Flag a query string that embeds a quoted literal directly in an equality
// or LIKE comparison (the sign of concatenation), as opposed to a "?"
// placeholder (the sign of binding).
fun hasInjectionSmell(sql: String): Boolean {
    TODO("t2: true when sql has a literal quoted value in a comparison instead of ?")
}

/** Whether an input was accepted outright or rejected outright. */
enum class ValidationResult { ACCEPTED, REJECTED }

// TODO(t3): T3ValidateUsernameTest
// Accept a username only if it is 3-20 characters, made entirely of
// letters, digits, and underscores. Anything else is rejected outright,
// there is no attempt to strip bad characters and re-check.
fun validateUsername(input: String): ValidationResult {
    TODO("t3: accept only input matching the allow-list, reject everything else as-is")
}

/** Whether a WebView-alike may load a URL, and separately, may expose its JS bridge to it. */
data class UrlLoadDecision(val mayLoad: Boolean, val exposeJsBridge: Boolean)

// TODO(t4): T4EvaluateUrlLoadTest
// Only http/https URLs may load at all, file/javascript/data URLs are
// always blocked regardless of host. Among URLs that may load, the JS
// bridge is only exposed when the URL's host is in trustedHosts.
fun evaluateUrlLoad(url: String, trustedHosts: Set<String>): UrlLoadDecision {
    TODO("t4: gate loading by scheme, gate the JS bridge by host membership in trustedHosts")
}

/** Raw signals coming back from an integrity check. */
enum class DeviceIntegrityVerdict { MEETS_STRONG_INTEGRITY, MEETS_DEVICE_INTEGRITY, MEETS_BASIC_INTEGRITY, NONE }
enum class AppIntegrityVerdict { PLAY_RECOGNIZED, UNRECOGNIZED_VERSION, UNEVALUATED }

data class RawIntegrityVerdict(
    val deviceIntegrity: DeviceIntegrityVerdict,
    val appIntegrity: AppIntegrityVerdict
)

/** The trust decision derived from a raw verdict. */
enum class TrustDecision { TRUSTED, SUSPICIOUS, UNTRUSTED }

// TODO(t5): T5ParseVerdictTest
// NONE device integrity is always UNTRUSTED, no matter the app signal.
// TRUSTED needs strong-or-device integrity together with a recognized app.
// Everything else (basic integrity, or an unrecognized/unevaluated app) is
// SUSPICIOUS.
fun parseVerdict(raw: RawIntegrityVerdict): TrustDecision {
    TODO("t5: map device+app integrity signals to TRUSTED/SUSPICIOUS/UNTRUSTED")
}

/** Where a trust decision actually came from. */
enum class VerdictSource { SERVER_VERIFIED, CLIENT_ONLY }

/** Thrown when a verdict didn't come from server-side verification. */
class UntrustedVerdictSourceException(message: String) : RuntimeException(message)

// TODO(t6): T6EnforceServerVerifiedTest
// A verdict is only actionable when it was verified server-side: a
// client-only check can be faked by a compromised device, so never trust
// one, even if the decision itself claims TRUSTED. Throw
// UntrustedVerdictSourceException for CLIENT_ONLY regardless of decision,
// otherwise return decision unchanged.
fun enforceServerVerified(source: VerdictSource, decision: TrustDecision): TrustDecision {
    TODO("t6: only SERVER_VERIFIED verdicts pass through; CLIENT_ONLY always throws")
}
