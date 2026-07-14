package validation

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

// Task 6: only a server-verified verdict is ever actionable.
class T6EnforceServerVerifiedTest {
    @Test fun `a server-verified trusted decision passes through`() {
        assertEquals(
            TrustDecision.TRUSTED,
            enforceServerVerified(VerdictSource.SERVER_VERIFIED, TrustDecision.TRUSTED)
        )
    }

    @Test fun `a server-verified untrusted decision passes through unchanged`() {
        assertEquals(
            TrustDecision.UNTRUSTED,
            enforceServerVerified(VerdictSource.SERVER_VERIFIED, TrustDecision.UNTRUSTED)
        )
    }

    @Test fun `a client-only trusted claim is never actionable`() {
        assertThrows(UntrustedVerdictSourceException::class.java) {
            enforceServerVerified(VerdictSource.CLIENT_ONLY, TrustDecision.TRUSTED)
        }
    }

    @Test fun `a client-only decision throws no matter what it claims`() {
        assertThrows(UntrustedVerdictSourceException::class.java) {
            enforceServerVerified(VerdictSource.CLIENT_ONLY, TrustDecision.UNTRUSTED)
        }
    }
}
