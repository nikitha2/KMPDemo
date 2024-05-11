import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kmpdemo.composeapp.generated.resources.Res
import kmpdemo.composeapp.generated.resources.compose_multiplatform
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun LocalTimeApplication(modifier: Modifier = Modifier.fillMaxWidth()) {
    Scaffold(
        modifier = modifier.padding(horizontal = 24.dp, vertical = 4.dp).fillMaxSize(),
        bottomBar = {
            PopulateNextScreenBtnContent(modifier = modifier) {

            }
        }) {
        var timeAtLocation by remember { mutableStateOf("No location selected") }
        var showCountries by remember { mutableStateOf(false) }

        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                timeAtLocation,
                style = TextStyle(fontSize = 20.sp),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)
            )
            Row(modifier = Modifier.padding(start = 20.dp, top = 10.dp)) {
                DropdownMenu(
                    expanded = showCountries,
                    onDismissRequest = { showCountries = false }
                ) {
                    countries().forEach { (name, zone, image) ->
                        DropdownMenuItem(
                            onClick = {
                                timeAtLocation = currentTimeAt(name, zone)
                                showCountries = false
                            }
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painterResource(image),
                                    modifier = Modifier.size(50.dp).padding(end = 10.dp),
                                    contentDescription = "$name flag"
                                )
                                Text(
                                    text = name
                                )
                            }

                        }
                    }
                }
            }
            Button(modifier = Modifier.padding(start = 20.dp, top = 10.dp),
                onClick = { showCountries = !showCountries }) {
                Text("Select Location")
            }

        }
    }
}

fun currentTimeAt(location: String, zone: TimeZone): String {
    fun LocalTime.formatted() = "$hour:$minute:$second"

    val time = Clock.System.now()
    val localTime = time.toLocalDateTime(zone).time

    return "The time in $location is ${localTime.formatted()}"
}

@OptIn(ExperimentalResourceApi::class)
fun countries() = listOf(
    Country("Japan", TimeZone.of("Asia/Tokyo"), Res.drawable.compose_multiplatform),
    Country("France", TimeZone.of("Europe/Paris"), Res.drawable.compose_multiplatform),
    Country("Mexico", TimeZone.of("America/Mexico_City"), Res.drawable.compose_multiplatform),
    Country("Indonesia", TimeZone.of("Asia/Jakarta"), Res.drawable.compose_multiplatform),
    Country("Egypt", TimeZone.of("Africa/Cairo"), Res.drawable.compose_multiplatform),
)

data class Country @OptIn(ExperimentalResourceApi::class) constructor(
    val name: String,
    val zone: TimeZone,
    val image: DrawableResource
)
