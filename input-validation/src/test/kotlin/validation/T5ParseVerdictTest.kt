package validation

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 5: parse a raw integrity verdict into a trust decision.
class T5ParseVerdictTest {
    @Test fun `strong device integrity with a recognized app is trusted`() {
        val verdict = RawIntegrityVerdict(DeviceIntegrityVerdict.MEETS_STRONG_INTEGRITY, AppIntegrityVerdict.PLAY_RECOGNIZED)
        assertEquals(TrustDecision.TRUSTED, parseVerdict(verdict))
    }

    @Test fun `no device integrity is untrusted even with a recognized app`() {
        val verdict = RawIntegrityVerdict(DeviceIntegrityVerdict.NONE, AppIntegrityVerdict.PLAY_RECOGNIZED)
        assertEquals(TrustDecision.UNTRUSTED, parseVerdict(verdict))
    }

    @Test fun `basic integrity only is suspicious`() {
        val verdict = RawIntegrityVerdict(DeviceIntegrityVerdict.MEETS_BASIC_INTEGRITY, AppIntegrityVerdict.PLAY_RECOGNIZED)
        assertEquals(TrustDecision.SUSPICIOUS, parseVerdict(verdict))
    }

    @Test fun `a good device with an unrecognized app is suspicious`() {
        val verdict = RawIntegrityVerdict(DeviceIntegrityVerdict.MEETS_STRONG_INTEGRITY, AppIntegrityVerdict.UNRECOGNIZED_VERSION)
        assertEquals(TrustDecision.SUSPICIOUS, parseVerdict(verdict))
    }
}
