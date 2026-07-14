package composestate

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// TODO(t1): T1CounterTest
// Show the count and an "Increment" button that raises it. The count must
// survive recomposition: without remember, every recomposition creates a
// fresh mutableStateOf(0), and the count would visibly reset to 0 right
// after the click that changed it.
@Composable
fun Counter(modifier: Modifier = Modifier) {
    TODO("t1: hold count in remember { mutableStateOf(0) } and render it")
}

// TODO(t2): T2SurvivingCounterTest
// Same shape as Counter, but the count must also survive process recreation
// (a configuration change, or the OS killing and restoring the activity).
// remember alone only survives recomposition, not recreation: swap it for
// rememberSaveable.
@Composable
fun SurvivingCounter(modifier: Modifier = Modifier) {
    TODO("t2: hold count in rememberSaveable { mutableStateOf(0) } and render it")
}

// TODO(t3): T3NameFieldTest
// Refactor a stateful text field into a stateless value + onValueChange pair.
// This composable must hold NO internal state of its own: everything it shows
// comes from `value`, and every edit is reported upward through onValueChange.
// That's state hoisting, it lets the caller decide what "changing the value"
// actually means (validate it, transform it, ignore it).
@Composable
fun NameField(value: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier) {
    TODO("t3: OutlinedTextField(value = value, onValueChange = onValueChange, modifier = modifier), no remember here")
}

// TODO(t4): T4LoginFormStateTest
// A state holder class groups several related pieces of state (and the logic
// over them) behind one object, instead of scattering separate remembers.
// Wrap LoginFormState's constructor in remember so the SAME instance (and its
// in-progress email/password) survives recomposition.
class LoginFormState {
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    val isValid: Boolean get() = email.contains("@") && password.length >= 6
}

@Composable
fun rememberLoginFormState(): LoginFormState {
    TODO("t4: return remember { LoginFormState() }")
}

@Composable
fun LoginForm(modifier: Modifier = Modifier, state: LoginFormState = rememberLoginFormState()) {
    Column(modifier = modifier.padding(16.dp)) {
        OutlinedTextField(
            value = state.email,
            onValueChange = { state.email = it },
            label = { Text("Email") },
        )
        OutlinedTextField(
            value = state.password,
            onValueChange = { state.password = it },
            label = { Text("Password") },
        )
        Text(if (state.isValid) "Valid" else "Invalid")
    }
}

// TODO(t5): T5PasswordStrengthFieldTest
// derivedStateOf recomposes its readers only when the DERIVED value actually
// changes, not on every read of the underlying state. Wrap the strength check
// in remember { derivedStateOf { } } so the badge below only recomposes when
// weak/strong flips, not on every keystroke.
@Composable
fun PasswordStrengthField(password: State<String>, onBadgeRecompose: () -> Unit, modifier: Modifier = Modifier) {
    TODO("t5: val isStrong by remember { derivedStateOf { password.value.length >= 8 } }; then StrengthBadge(isStrong, onBadgeRecompose)")
}

@Composable
private fun StrengthBadge(isStrong: Boolean, onRecompose: () -> Unit, modifier: Modifier = Modifier) {
    SideEffect { onRecompose() }
    Text(text = if (isStrong) "Strong" else "Weak", modifier = modifier)
}

// TODO(t6): T6TodoListScreenTest
// A plain remember { mutableListOf<String>() } is not observable: mutating it
// with .add() changes the list object in place, but Compose has no snapshot
// to notice, so nothing recomposes and the UI goes stale. Back this list with
// mutableStateListOf() instead, whose mutations ARE observed.
@Composable
fun TodoListScreen(modifier: Modifier = Modifier) {
    TODO("t6: val items = remember { mutableStateListOf<String>() }; add items on button click; render items.size and each item")
}
