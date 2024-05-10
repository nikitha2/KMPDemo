import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun PopulateNextScreenBtnContent(modifier: Modifier, onNextScreenBtnClickListener:@Composable () -> Unit = {}) {
    var openNextScreen by remember { mutableStateOf(false) }

    Button(modifier=modifier,
           onClick = { openNextScreen = !openNextScreen}) {
        Text("Next Screen")
    }
    AnimatedVisibility(openNextScreen) {
        onNextScreenBtnClickListener.invoke()
    }
}

@Composable
fun OnNextBtnClicked() {
    var openNextScreen by remember { mutableStateOf(false) }
    Button(
        onClick = { openNextScreen = !openNextScreen}) {
        Text("Next Screen")
    }
}