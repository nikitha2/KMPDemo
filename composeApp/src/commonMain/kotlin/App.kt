import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import kmpdemo.composeapp.generated.resources.Res
import kmpdemo.composeapp.generated.resources.compose_multiplatform
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import androidx.compose.material.Scaffold

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        Scaffold(
            bottomBar = {
                PopulateNextScreenBtnContent(Modifier.fillMaxWidth().padding(horizontal = 24.dp)) {
                    LocalTimeApplication()
                }
            }) {
            var showContent by remember { mutableStateOf(false) }
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Today's date is ${todaysDate()}",
                    modifier = Modifier.padding(20.dp),
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )

                Button(onClick = { showContent = !showContent }) {
                    Text("Click me!")
                }
                AnimatedVisibility(showContent) {
                    val greeting = remember { Greeting().greet() }
                    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(painterResource(Res.drawable.compose_multiplatform), null)
                        Text("Compose: $greeting")
                    }
                }
            }
        }
    }
}

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


fun todaysDate(): String {
    fun LocalDateTime.format() = toString().substringBefore('T')

    val now = Clock.System.now()
    val zone = TimeZone.currentSystemDefault()
    return now.toLocalDateTime(zone).format()
}