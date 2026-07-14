package composeperf

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import kotlinx.collections.immutable.persistentListOf
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
class T5StableListRowTest {
    @get:Rule val compose = createComposeRule()

    @Test fun `skips recomposition when a content-equal ImmutableList arrives`() {
        var trigger by mutableStateOf(0)
        var recomposeCount = 0
        // Hoisted so the SAME lambda instance is passed every recomposition, the
        // same reason as in T3: an inline lambda here would itself look "changed"
        // on every recomposition and mask what we're testing.
        val onRecompose: () -> Unit = { recomposeCount++ }

        compose.setContent {
            MaterialTheme {
                @Suppress("UNUSED_EXPRESSION") trigger
                // A fresh PersistentList instance with the same two elements every
                // time. ImmutableList is stable, so Compose can skip this row.
                StableListRow(items = persistentListOf("a", "b"), onRecompose = onRecompose)
            }
        }
        compose.onNodeWithText("Items: 2").assertIsDisplayed()
        assertEquals(1, recomposeCount)

        trigger = 1
        compose.waitForIdle()
        assertEquals(1, recomposeCount)

        trigger = 2
        compose.waitForIdle()
        assertEquals(1, recomposeCount)
    }
}
