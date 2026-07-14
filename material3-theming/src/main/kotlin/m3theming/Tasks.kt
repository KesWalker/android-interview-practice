package m3theming

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowWidthSizeClass

// TODO(t1): T1ThemedBannerTest
// Build a small MaterialTheme colour token in action: a Surface whose fill
// colour comes from MaterialTheme.colorScheme.primary, with text coloured by
// MaterialTheme.colorScheme.onPrimary and styled by MaterialTheme.typography.
// This composable does NOT declare its own MaterialTheme; it reads whatever
// theme the caller wraps it in. That's the whole point: tokens flow down.
@Composable
fun ThemedBanner(modifier: Modifier = Modifier) {
    TODO(
        "t1: Surface(color = MaterialTheme.colorScheme.primary) containing " +
            "Text(\"Themed\", color = MaterialTheme.colorScheme.onPrimary, " +
            "style = MaterialTheme.typography.titleLarge). Tag the Surface " +
            "with Modifier.testTag(\"themedBanner\")."
    )
}

val LightPalette = lightColorScheme(
    primary = Color(0xFF6750A4),
    surface = Color(0xFFFFFBFE),
    onSurface = Color(0xFF1C1B1F),
)

val DarkPalette = darkColorScheme(
    primary = Color(0xFFD0BCFF),
    surface = Color(0xFF1C1B1F),
    onSurface = Color(0xFFE6E1E5),
)

// TODO(t2): T2ThemedStatusPillTest
// Pick a ColorScheme based on isDarkTheme (use the LightPalette/DarkPalette
// above), wrap the content in a MaterialTheme built from that scheme, and
// render a Surface using MaterialTheme.colorScheme.surface / onSurface. Label
// the pill "Dark mode" or "Light mode" to match.
@Composable
fun ThemedStatusPill(isDarkTheme: Boolean, modifier: Modifier = Modifier) {
    TODO(
        "t2: val scheme = if (isDarkTheme) DarkPalette else LightPalette; " +
            "MaterialTheme(colorScheme = scheme) { Surface(color = " +
            "MaterialTheme.colorScheme.surface, testTag \"statusPill\") { " +
            "Text(if (isDarkTheme) \"Dark mode\" else \"Light mode\", " +
            "color = MaterialTheme.colorScheme.onSurface) } }"
    )
}

// TODO(t3): T3ProfileScreenTest
// Compose a small real screen: Scaffold with a TopAppBar titled "Profile",
// a Card showing a name, and a Button labelled "Save" that invokes onSaveClick.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(onSaveClick: () -> Unit, modifier: Modifier = Modifier) {
    TODO(
        "t3: Scaffold(topBar = { TopAppBar(title = { Text(\"Profile\") }) }) " +
            "{ padding -> Column(Modifier.padding(padding)) { " +
            "Card { Text(\"Kes Walker\") }; Button(onClick = onSaveClick) " +
            "{ Text(\"Save\") } } }"
    )
}

// TODO(t4): T4WarningLabelTest
// This is the lesson task: a warning banner that must read its colour from
// MaterialTheme.colorScheme.error (and onError for the text), never a
// hardcoded Color like Color.Red. A caller can retheme the whole app and this
// banner must follow along.
@Composable
fun WarningLabel(text: String, modifier: Modifier = Modifier) {
    TODO(
        "t4: Surface(modifier.testTag(\"warningLabel\"), color = " +
            "MaterialTheme.colorScheme.error) { Text(text, color = " +
            "MaterialTheme.colorScheme.onError) }. Do not hardcode a Color."
    )
}

// TODO(t5): T5AdaptiveHomeScreenTest
// Real adaptive layout: below the COMPACT breakpoint (< 600dp) show a bottom
// NavigationBar; at MEDIUM/EXPANDED widths (>= 600dp) show a side
// NavigationRail instead. Tag the NavigationBar "bottomBar" and the
// NavigationRail "navRail" so the test can tell which one rendered.
@Composable
fun AdaptiveHomeScreen(windowWidthSizeClass: WindowWidthSizeClass, modifier: Modifier = Modifier) {
    TODO(
        "t5: if (windowWidthSizeClass == WindowWidthSizeClass.COMPACT) " +
            "Scaffold(bottomBar = { NavigationBar(Modifier.testTag(\"bottomBar\")) " +
            "{ ...NavigationBarItem per destination... } }) { padding -> " +
            "Text(\"Content\", Modifier.padding(padding)) } else Row { " +
            "NavigationRail(Modifier.testTag(\"navRail\")) { " +
            "...NavigationRailItem per destination... }; Text(\"Content\") }"
    )
}
