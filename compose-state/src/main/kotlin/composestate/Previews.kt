package composestate

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true, widthDp = 240, heightDp = 160)
@Composable
fun CounterPreview() {
    MaterialTheme { Surface { Counter() } }
}

@Preview(showBackground = true, widthDp = 240, heightDp = 160)
@Composable
fun SurvivingCounterPreview() {
    MaterialTheme { Surface { SurvivingCounter() } }
}

@Preview(showBackground = true, widthDp = 260, heightDp = 100)
@Composable
fun NameFieldPreview() {
    MaterialTheme { Surface { NameField(value = "Ada", onValueChange = {}) } }
}

@Preview(showBackground = true, widthDp = 280, heightDp = 220)
@Composable
fun LoginFormPreview() {
    MaterialTheme { Surface { LoginForm() } }
}

@Preview(showBackground = true, widthDp = 200, heightDp = 100)
@Composable
fun PasswordStrengthFieldPreview() {
    MaterialTheme { Surface { PasswordStrengthField(password = mutableStateOf("abcdefgh"), onBadgeRecompose = {}) } }
}

@Preview(showBackground = true, widthDp = 260, heightDp = 220)
@Composable
fun TodoListScreenPreview() {
    MaterialTheme { Surface { TodoListScreen() } }
}
