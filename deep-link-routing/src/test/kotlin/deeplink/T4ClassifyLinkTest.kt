package deeplink

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 4: check the scheme first, then whether the host is in the allowlist.
class T4ClassifyLinkTest {
    private val verifiedHosts = setOf("audatours.com", "www.audatours.com")

    @Test fun `https on a verified host is a verified app link`() {
        val uri = ParsedUri("https", "audatours.com", listOf("tour", "42"), emptyMap())
        assertEquals(LinkKind.VERIFIED_APP_LINK, classifyLink(uri, verifiedHosts))
    }

    @Test fun `http on a verified host is also a verified app link`() {
        val uri = ParsedUri("http", "www.audatours.com", listOf("tour", "42"), emptyMap())
        assertEquals(LinkKind.VERIFIED_APP_LINK, classifyLink(uri, verifiedHosts))
    }

    @Test fun `https on an unlisted host is an unverified web link`() {
        val uri = ParsedUri("https", "evil.example.com", listOf("tour", "42"), emptyMap())
        assertEquals(LinkKind.UNVERIFIED_WEB_LINK, classifyLink(uri, verifiedHosts))
    }

    @Test fun `custom scheme is never verified`() {
        val uri = ParsedUri("audatours", "audatours.com", listOf("tour", "42"), emptyMap())
        assertEquals(LinkKind.CUSTOM_SCHEME, classifyLink(uri, verifiedHosts))
    }
}
