package coilimages

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ApplicationProvider
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.test.FakeImageLoaderEngine
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@OptIn(ExperimentalCoilApi::class)
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w360dp-h640dp-xhdpi")
class T5SwappableImageTest {
    @get:Rule val compose = createComposeRule()

    @Test fun `always reflects the current model after swapping, never a stale one`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val urlA = "https://example.com/a.jpg"
        val urlB = "https://example.com/b.jpg"
        val engine = FakeImageLoaderEngine.Builder()
            .intercept(urlA, ColorDrawable(Color.RED))
            .intercept(urlB, ColorDrawable(Color.BLUE))
            .build()
        val imageLoader = ImageLoader.Builder(context).components { add(engine) }.build()

        var model by mutableStateOf<Any?>(urlA)
        compose.setContent {
            MaterialTheme { SwappableImage(model = model, imageLoader = imageLoader) }
        }
        compose.waitForIdle()
        compose.onNodeWithText("$urlA: Loaded").assertIsDisplayed()

        model = urlB
        compose.waitForIdle()
        compose.onNodeWithText("$urlB: Loaded").assertIsDisplayed()

        model = urlA
        compose.waitForIdle()
        compose.onNodeWithText("$urlA: Loaded").assertIsDisplayed()
    }
}
