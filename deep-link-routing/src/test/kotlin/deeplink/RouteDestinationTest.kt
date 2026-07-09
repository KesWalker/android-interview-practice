package deeplink

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 3: check patterns in order with matchPath, falling through to Unknown.
class RouteDestinationTest {
    @Test fun `empty path routes home`() {
        val uri = ParsedUri("audatours", "app", emptyList(), emptyMap())
        assertEquals(Destination.Home, route(uri))
    }

    @Test fun `tour path routes to tour detail with lang`() {
        val uri = ParsedUri("audatours", "app", listOf("tour", "42"), mapOf("lang" to "en"))
        assertEquals(Destination.TourDetail("42", "en"), route(uri))
    }

    @Test fun `tour path with no lang query param`() {
        val uri = ParsedUri("audatours", "app", listOf("tour", "7"), emptyMap())
        assertEquals(Destination.TourDetail("7", null), route(uri))
    }

    @Test fun `search path routes to search with q`() {
        val uri = ParsedUri("audatours", "app", listOf("search"), mapOf("q" to "colosseum"))
        assertEquals(Destination.Search("colosseum"), route(uri))
    }

    @Test fun `unrecognised path routes to unknown`() {
        val uri = ParsedUri("audatours", "app", listOf("settings", "profile"), emptyMap())
        assertEquals(Destination.Unknown("settings/profile"), route(uri))
    }
}
