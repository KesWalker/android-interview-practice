package pinning

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 5: cleartext is blocked by default; a per-domain override wins.
class T5IsCleartextPermittedTest {
    @Test fun `no override falls back to blocked default`() {
        val config = NetworkSecurityConfig(cleartextPermittedByDefault = false)
        assertFalse(isCleartextPermitted(config, "example.com"))
    }

    @Test fun `no override falls back to a permissive default`() {
        val config = NetworkSecurityConfig(cleartextPermittedByDefault = true)
        assertTrue(isCleartextPermitted(config, "example.com"))
    }

    @Test fun `domain override permits cleartext despite a blocked default`() {
        val config = NetworkSecurityConfig(
            cleartextPermittedByDefault = false,
            domainOverrides = listOf(DomainConfig("legacy.example.com", cleartextPermitted = true))
        )
        assertTrue(isCleartextPermitted(config, "legacy.example.com"))
    }

    @Test fun `domain override blocks cleartext despite a permissive default`() {
        val config = NetworkSecurityConfig(
            cleartextPermittedByDefault = true,
            domainOverrides = listOf(DomainConfig("secure.example.com", cleartextPermitted = false))
        )
        assertFalse(isCleartextPermitted(config, "secure.example.com"))
    }
}
