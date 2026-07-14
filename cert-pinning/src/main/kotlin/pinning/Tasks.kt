package pinning

/**
 * Certificate pinning and network security config practice.
 *
 * Each function below is broken or unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to
 * implement it so its test goes GREEN. Run a single test class from the
 * gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :cert-pinning:test
 *
 * Hashes are modelled as opaque strings, there's no real crypto here.
 */

/** One certificate in a chain. `spkiHash` stands in for a real SPKI pin hash. */
data class Certificate(
    val subject: String,
    val spkiHash: String,
    val issuerSubject: String,
    val notBefore: Long,
    val notAfter: Long
)

// TODO(t1): T1ValidateChainTest
// A chain is ordered leaf, then intermediate(s), then root. It's valid at
// `atTime` only when every cert's issuerSubject matches the subject of the
// next cert in the list, the last cert (the root) is self-signed
// (its issuerSubject equals its own subject), and every cert in the chain
// is within its [notBefore, notAfter] validity window at atTime. An empty
// chain is never valid.
fun validateChain(chain: List<Certificate>, atTime: Long): Boolean {
    TODO("t1: each cert signed by the next, root self-signed, none expired at atTime")
}

// TODO(t2): T2PinsMatchTest
// A pin set matches a chain if ANY certificate in the chain has an
// spkiHash present in `pins`. It doesn't matter which position in the
// chain matches.
fun pinsMatch(chain: List<Certificate>, pins: Set<String>): Boolean {
    TODO("t2: true when any cert in the chain has a hash in pins")
}

// TODO(t3): T3IsFragilePinningTest
// A pin configuration is fragile when the leaf (index 0) is the ONLY
// certificate in the chain that any pin matches. Pinning just the leaf
// breaks the app the moment that cert rotates. If a pin also matches an
// intermediate or the root, the config isn't fragile, that cert survives
// leaf rotation. If no cert matches at all, this isn't fragility either,
// it's just not pinned here.
fun isFragilePinning(chain: List<Certificate>, pins: Set<String>): Boolean {
    TODO("t3: true only when the leaf is matched and nothing else is")
}

/** Thrown when a pin set is too thin to survive a cert rotation. */
class InsufficientPinsException(message: String) : RuntimeException(message)

// TODO(t4): T4EnsureBackupPinTest
// A single pin bricks the app the moment that certificate rotates, there's
// nothing left to fall back on. Require at least two pins in the set;
// throw InsufficientPinsException when there are fewer than that.
fun ensureBackupPin(pins: Set<String>) {
    TODO("t4: throw InsufficientPinsException when pins.size is less than 2")
}

/** A per-domain cleartext override from a network security config. */
data class DomainConfig(val domain: String, val cleartextPermitted: Boolean)

/** A simplified network security config: default policy plus per-domain overrides. */
data class NetworkSecurityConfig(
    val cleartextPermittedByDefault: Boolean = false,
    val domainOverrides: List<DomainConfig> = emptyList(),
    val debugOverridesTrustUserCas: Boolean = false
)

// TODO(t5): T5IsCleartextPermittedTest
// Cleartext traffic is blocked by default. If `domain` has an entry in
// domainOverrides, use that entry's cleartextPermitted value. Otherwise
// fall back to cleartextPermittedByDefault.
fun isCleartextPermitted(config: NetworkSecurityConfig, domain: String): Boolean {
    TODO("t5: per-domain override wins, otherwise fall back to the default policy")
}

// TODO(t6): T6IsUserCaTrustedTest
// A <debug-overrides> user-CA trust setting only ever applies to debug
// builds. Even if the config enables debugOverridesTrustUserCas, a release
// build must never trust user-installed CAs.
fun isUserCaTrusted(config: NetworkSecurityConfig, isDebugBuild: Boolean): Boolean {
    TODO("t6: user CAs are trusted only when the config allows it AND it's a debug build")
}
