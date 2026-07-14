package pinning

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 6: debug-overrides user-CA trust never leaks into release builds.
class T6IsUserCaTrustedTest {
    @Test fun `debug build with the override enabled trusts user CAs`() {
        val config = NetworkSecurityConfig(debugOverridesTrustUserCas = true)
        assertTrue(isUserCaTrusted(config, isDebugBuild = true))
    }

    @Test fun `release build never trusts user CAs even with the override enabled`() {
        val config = NetworkSecurityConfig(debugOverridesTrustUserCas = true)
        assertFalse(isUserCaTrusted(config, isDebugBuild = false))
    }

    @Test fun `debug build without the override does not trust user CAs`() {
        val config = NetworkSecurityConfig(debugOverridesTrustUserCas = false)
        assertFalse(isUserCaTrusted(config, isDebugBuild = true))
    }
}
