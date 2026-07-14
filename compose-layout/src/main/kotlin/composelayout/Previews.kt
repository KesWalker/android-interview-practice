package composelayout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true, widthDp = 320, heightDp = 80)
@Composable
fun ArrangedRowPreview() {
    MaterialTheme { Surface { ArrangedRow() } }
}

@Preview(showBackground = true, widthDp = 200, heightDp = 200)
@Composable
fun ModifierOrderBoxPreview() {
    MaterialTheme { Surface { ModifierOrderBox(paddingFirst = false) } }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 80)
@Composable
fun WeightedRowPreview() {
    MaterialTheme { Surface { WeightedRow() } }
}

@Preview(showBackground = true, widthDp = 160, heightDp = 160)
@Composable
fun CascadeLayoutPreview() {
    MaterialTheme {
        Surface {
            CascadeLayout {
                Box(Modifier.size(40.dp).background(Color(0xFF6650A4)))
                Box(Modifier.size(40.dp).background(Color(0xFF625B71)))
                Box(Modifier.size(40.dp).background(Color(0xFF7D5260)))
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 240, heightDp = 100)
@Composable
fun IntrinsicWidthColumnPreview() {
    MaterialTheme { Surface { IntrinsicWidthColumn() } }
}

@Preview(showBackground = true, widthDp = 300, heightDp = 200)
@Composable
fun ToggleListPreview() {
    MaterialTheme {
        Surface {
            ToggleList(
                items = listOf(
                    ToggleItem("1", "First"),
                    ToggleItem("2", "Second"),
                    ToggleItem("3", "Third"),
                )
            )
        }
    }
}
