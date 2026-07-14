package m3theming

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.window.core.layout.WindowSizeClass

@Preview(showBackground = true, widthDp = 260, heightDp = 120)
@Composable
fun ThemedBannerPreview() {
    MaterialTheme(colorScheme = LightPalette) {
        Surface { ThemedBanner() }
    }
}

@Preview(showBackground = true, widthDp = 220, heightDp = 100)
@Composable
fun ThemedStatusPillLightPreview() {
    ThemedStatusPill(isDarkTheme = false)
}

@Preview(showBackground = true, widthDp = 220, heightDp = 100)
@Composable
fun ThemedStatusPillDarkPreview() {
    ThemedStatusPill(isDarkTheme = true)
}

@Preview(showBackground = true, widthDp = 360, heightDp = 500)
@Composable
fun ProfileScreenPreview() {
    MaterialTheme(colorScheme = LightPalette) {
        ProfileScreen(onSaveClick = {})
    }
}

@Preview(showBackground = true, widthDp = 260, heightDp = 100)
@Composable
fun WarningLabelPreview() {
    MaterialTheme {
        WarningLabel(text = "Low battery")
    }
}

@Preview(name = "Adaptive - compact phone", showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun AdaptiveHomeScreenCompactPreview() {
    val widthClass = WindowSizeClass.compute(360f, 640f).windowWidthSizeClass
    MaterialTheme {
        AdaptiveHomeScreen(windowWidthSizeClass = widthClass)
    }
}

@Preview(name = "Adaptive - medium tablet", showBackground = true, widthDp = 700, heightDp = 500)
@Composable
fun AdaptiveHomeScreenMediumPreview() {
    val widthClass = WindowSizeClass.compute(700f, 500f).windowWidthSizeClass
    MaterialTheme {
        AdaptiveHomeScreen(windowWidthSizeClass = widthClass)
    }
}

@Preview(name = "Adaptive - expanded desktop", showBackground = true, widthDp = 1000, heightDp = 500)
@Composable
fun AdaptiveHomeScreenExpandedPreview() {
    val widthClass = WindowSizeClass.compute(1000f, 500f).windowWidthSizeClass
    MaterialTheme {
        AdaptiveHomeScreen(windowWidthSizeClass = widthClass)
    }
}
