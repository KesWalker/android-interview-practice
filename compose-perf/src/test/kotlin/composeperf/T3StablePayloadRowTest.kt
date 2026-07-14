package composeperf

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w360dp-h640dp-xhdpi")
class T3StablePayloadRowTest {
    @get:Rule val compose = createComposeRule()

    @Test fun `skips recomposition when a content-equal instance arrives`() {
        var trigger by mutableStateOf(0)
        var recomposeCount = 0
        // Hoisted so the SAME lambda instance is passed every recomposition. An
        // inline `{ recomposeCount++ }` written directly at the call site below
        // would itself be a fresh, unmemoized value on every recomposition of
        // this content block, which alone would force StablePayloadRow to
        // recompose regardless of payload stability, masking what we're testing.
        val onRecompose: () -> Unit = { recomposeCount++ }

        compose.setContent {
            MaterialTheme {
                @Suppress("UNUSED_EXPRESSION") trigger
                // A brand new instance with the same label every time. Unlike
                // UnstablePayload, StablePayload is @Immutable and content-equal,
                // so Compose can trust it and skip recomposing this row.
                StablePayloadRow(payload = StablePayload("Ada"), onRecompose = onRecompose)
            }
        }
        compose.onNodeWithText("Ada").assertIsDisplayed()
        assertEquals(1, recomposeCount)

        trigger = 1
        compose.waitForIdle()
        assertEquals(1, recomposeCount)

        trigger = 2
        compose.waitForIdle()
        assertEquals(1, recomposeCount)
    }
}
