package validation

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Task 4: WebView-alike load and JS-bridge exposure policy.
class T4EvaluateUrlLoadTest {
    private val trustedHosts = setOf("trusted.example.com")

    @Test fun `a trusted https url may load and gets the js bridge`() {
        assertEquals(
            UrlLoadDecision(mayLoad = true, exposeJsBridge = true),
            evaluateUrlLoad("https://trusted.example.com/page", trustedHosts)
        )
    }

    @Test fun `an untrusted https url may load but does not get the js bridge`() {
        assertEquals(
            UrlLoadDecision(mayLoad = true, exposeJsBridge = false),
            evaluateUrlLoad("https://evil.example.com/page", trustedHosts)
        )
    }

    @Test fun `a file url is always blocked, even claiming a trusted host`() {
        assertEquals(
            UrlLoadDecision(mayLoad = false, exposeJsBridge = false),
            evaluateUrlLoad("file:///etc/passwd", trustedHosts)
        )
    }

    @Test fun `a javascript url is always blocked`() {
        assertEquals(
            UrlLoadDecision(mayLoad = false, exposeJsBridge = false),
            evaluateUrlLoad("javascript:alert(1)", trustedHosts)
        )
    }
}
