package broadcast

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Task 4: why implicit broadcasts to manifest-declared receivers are restricted.
class T4ImplicitBroadcastAllowedTest {
    private val filter = IntentFilterSpec(actions = setOf("android.intent.action.BOOT_COMPLETED"))

    @Test fun `a dynamically registered receiver is always eligible`() {
        val receiver = Receiver("dyn", filter, Registration.DYNAMIC)
        assertTrue(implicitBroadcastAllowed(receiver, BroadcastIntent("android.intent.action.BOOT_COMPLETED")))
        assertTrue(implicitBroadcastAllowed(receiver, BroadcastIntent("some.random.ACTION")))
    }

    @Test fun `a manifest receiver is only eligible for an exempt action`() {
        val receiver = Receiver("mf", filter, Registration.MANIFEST)
        assertTrue(implicitBroadcastAllowed(receiver, BroadcastIntent("android.intent.action.BOOT_COMPLETED")))
        assertFalse(implicitBroadcastAllowed(receiver, BroadcastIntent("com.example.SOME_CUSTOM_ACTION")))
    }
}
