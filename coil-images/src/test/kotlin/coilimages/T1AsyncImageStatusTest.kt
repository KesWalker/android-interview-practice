package coilimages

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
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
class T1AsyncImageStatusTest {
    @get:Rule val compose = createComposeRule()

    @Test fun `settles into Loaded once the request succeeds`() {
        val url = "https://example.com/photo.jpg"
        val context = ApplicationProvider.getApplicationContext<Context>()
        val engine = FakeImageLoaderEngine.Builder()
            .intercept(url, ColorDrawable(Color.RED))
            .build()
        val imageLoader = ImageLoader.Builder(context).components { add(engine) }.build()

        compose.setContent {
            MaterialTheme {
                AsyncImageStatus(model = url, imageLoader = imageLoader, contentDescription = "A photo")
            }
        }
        compose.waitForIdle()

        compose.onNodeWithText("Status: Loaded").assertIsDisplayed()
        compose.onNodeWithContentDescription("A photo").assertIsDisplayed()
    }
}
