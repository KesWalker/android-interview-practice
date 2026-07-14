package composeeffects

import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import sergio.sastre.composable.preview.scanner.android.AndroidComposablePreviewScanner

/**
 * Renders every @Preview in this module to build/previews/<name>.png.
 *
 *     ./gradlew :compose-effects:recordRoborazziDebug
 *
 * This is a TOOL, not a graded task, so it never fails the build. A preview
 * whose task is still unimplemented throws while rendering: it's reported as
 * PENDING and the others still render. So as you implement each task, its
 * preview starts appearing.
 */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34])
class PreviewScreenshotTest {
    @Test
    fun renderEveryPreview() {
        val previews = AndroidComposablePreviewScanner()
            .scanPackageTrees("composeeffects")
            .getPreviews()

        val rendered = mutableListOf<String>()
        val pending = mutableListOf<String>()
        previews.forEach { preview ->
            val name = preview.methodName
            try {
                preview.captureRoboImage("build/previews/$name.png")
                rendered += name
            } catch (t: Throwable) {
                pending += name
            }
        }
        println("PREVIEWS rendered=${rendered.size} $rendered")
        println("PREVIEWS pending=${pending.size} $pending")
    }
}
