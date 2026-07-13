package deeplink

/**
 * Deep-Link URI Parsing & Routing practice.
 *
 * Each function below is broken or unwritten and has a matching test in
 * src/test that is currently RED. Your job, one task at a time, is to make
 * the code correct so its test goes GREEN. Run a single test class from the
 * gutter in Android Studio, or run them all with:
 *
 *     ./gradlew :deep-link-routing:test
 *
 * The types below (ParsedUri, Destination, LinkKind) are already complete -
 * you're only filling in the functions.
 */

/** A deep-link URI broken into its parts, e.g. "audatours://app/tour/42?lang=en". */
data class ParsedUri(
    val scheme: String,
    val host: String,
    val pathSegments: List<String>,
    val queryParams: Map<String, String>,
)

/** The typed screens this app can be routed to. */
sealed class Destination {
    data class TourDetail(val tourId: String, val lang: String?) : Destination()
    data class Search(val query: String?) : Destination()
    object Home : Destination()
    data class Unknown(val path: String) : Destination()
}

/** How a link was classified against the app's verified-domain allowlist. */
enum class LinkKind { VERIFIED_APP_LINK, UNVERIFIED_WEB_LINK, CUSTOM_SCHEME }

// TODO(t1): T1ParseUriTest
// Split a raw URI string of the form "scheme://host/seg1/seg2?k=v&k2=v2" into
// its scheme, host, path segments (no empty segments), and query params. A
// missing query string yields an empty map.
fun parseUri(raw: String): ParsedUri {
    TODO("t1: split raw into scheme, host, pathSegments, and queryParams")
}

// TODO(t2): T2MatchPathTest
// Compare `segments` against a template like "tour/{id}", where a "{name}"
// piece matches any single segment and is captured under "name". Return null
// when the segment counts differ or a literal piece doesn't match.
fun matchPath(pattern: String, segments: List<String>): Map<String, String>? {
    TODO("t2: match segments against pattern, capturing {placeholder} values")
}

// TODO(t3): T3RouteDestinationTest
// Turn a ParsedUri into a Destination: empty path -> Home; path matching
// "tour/{id}" -> TourDetail with that id and the "lang" query param; path
// matching "search" -> Search with the "q" query param; anything else ->
// Unknown holding the joined path.
fun route(uri: ParsedUri): Destination {
    TODO("t3: dispatch uri to the right Destination using matchPath")
}

// TODO(t4): T4ClassifyLinkTest
// Classify a link: http/https with a host in `verifiedHosts` is a verified
// app link; http/https with any other host is an unverified web link;
// anything else (a custom scheme) is a custom-scheme link.
fun classifyLink(uri: ParsedUri, verifiedHosts: Set<String>): LinkKind {
    TODO("t4: classify uri as verified app link, unverified web link, or custom scheme")
}

/** An implicit intent: an action plus the categories it carries. */
data class IntentSpec(
    val action: String,
    val categories: Set<String> = emptySet(),
)

/** An intent-filter as declared in the manifest. */
data class IntentFilterSpec(
    val actions: Set<String>,
    val categories: Set<String>,
)

// TODO(t5): T5MatchesFilterTest
// Decide whether an implicit IntentSpec (an action plus the categories it
// carries, exactly what startActivity() auto-stamps CATEGORY_DEFAULT onto)
// matches a declared IntentFilterSpec's action and category lists - the action
// and category tests Android runs before ever considering intent-filter data.
fun matchesFilter(intent: IntentSpec, filter: IntentFilterSpec): Boolean {
    TODO("t5: match the intent's action against the filter, then every intent category")
}
