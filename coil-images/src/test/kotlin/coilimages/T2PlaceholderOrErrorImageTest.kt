package coilimages

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.compose.material3.MaterialTheme
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
class T2PlaceholderOrErrorImageTest {
    @get:Rule val compose = createComposeRule()

    private val context get() = ApplicationProvider.getApplicationContext<Context>()

    @Test fun `shows a placeholder when there is no model, without firing a request`() {
        val imageLoader = ImageLoader.Builder(context).build()

        compose.setContent {
            MaterialTheme {
                PlaceholderOrErrorImage(model = null, imageLoader = imageLoader, contentDescription = "A photo")
            }
        }

        compose.onNodeWithText("No image").assertIsDisplayed()
    }

    @Test fun `shows a fallback message when the request fails`() {
        val engine = FakeImageLoaderEngine.Builder().build() // no interceptors, no default: every request errors
        val imageLoader = ImageLoader.Builder(context).components { add(engine) }.build()

        compose.setContent {
            MaterialTheme {
                PlaceholderOrErrorImage(
                    model = "https://example.com/missing.jpg",
                    imageLoader = imageLoader,
                    contentDescription = "A photo",
                )
            }
        }
        compose.waitForIdle()

        compose.onNodeWithText("Couldn't load image").assertIsDisplayed()
    }

    @Test fun `renders the real image once the request succeeds`() {
        val url = "https://example.com/photo.jpg"
        val engine = FakeImageLoaderEngine.Builder()
            .intercept(url, ColorDrawable(Color.BLUE))
            .build()
        val imageLoader = ImageLoader.Builder(context).components { add(engine) }.build()

        compose.setContent {
            MaterialTheme {
                PlaceholderOrErrorImage(model = url, imageLoader = imageLoader, contentDescription = "A photo")
            }
        }
        compose.waitForIdle()

        compose.onNodeWithText("Loaded").assertIsDisplayed()
    }
}
