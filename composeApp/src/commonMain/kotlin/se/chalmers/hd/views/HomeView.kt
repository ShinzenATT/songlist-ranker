package se.chalmers.hd.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import io.ktor.client.plugins.*
import org.jetbrains.compose.ui.tooling.preview.Preview
import se.chalmers.hd.dto.Song
import se.chalmers.hd.services.getRandomSong

class HomeView: ScreenView {
    override var rootPadding: PaddingValues = PaddingValues()

    @Composable
    @Preview
    override fun Content() {
        var search by remember { mutableStateOf("") }
        var song by remember { mutableStateOf<Song?>(null) }
        val navigator = LocalNavigator.current

        LaunchedEffect(Unit) {
            try {
                song = getRandomSong()
            } catch (e: ServerResponseException) {
                e.printStackTrace()
            }
        }

        Column(
            Modifier.fillMaxWidth().fillMaxHeight().padding(rootPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "HD-sektionens sånglista",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 20.dp, top = 60.dp)
            )
            ElevatedCard(
                ///TODO: Add on click to navigate to the song.
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp,
                ),
                modifier = Modifier.fillMaxHeight(fraction = 0.7f).fillMaxWidth(fraction = 0.90f),
            ) {
                if (song == null) {
                    CircularProgressIndicator(Modifier.fillMaxSize())
                } else {
                    Row(modifier = Modifier.padding(start = 5.dp)) {
                        Column() {
                            Text(
                                text = song!!.title,
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier.padding(start = 10.dp, top = 20.dp, bottom = 10.dp)
                            )
                            if (song!!.melody != null) {
                                Text(
                                    text = song!!.melody!!.name,
                                    style = MaterialTheme.typography.titleSmall,
                                    color = MaterialTheme.colorScheme.onBackground,
                                    modifier = Modifier.padding(start = 20.dp, bottom = 10.dp)
                                )
                            }
                        }

                    }

                    HorizontalDivider(
                        Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                        thickness = 2.dp
                    )
                    Text(
                        modifier = Modifier.padding(10.dp),
                        overflow = TextOverflow.Ellipsis,
                        text = song!!.content
                    )
                }
            }
            ElevatedButton(
                onClick = {
                    navigator?.popUntilRoot()
                    navigator?.push(HomeView())
                }
            ){
                Text("Jag har tur!")
            }
            Row(verticalAlignment = Alignment.Bottom) {
                TextField(
                    value = search,
                    modifier = Modifier.fillMaxWidth(fraction = 0.95F).padding(vertical = 5.dp),
                    onValueChange = { search = it },
                    placeholder = { Text("Sök efter sångtitel, lyrik eller ID") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(onSearch = { navigator!!.push(SongListView(search)) }),
                    trailingIcon = {
                        FilledTonalButton(
                            onClick = { navigator!!.push(SongListView(search)) },
                            enabled = true,
                        ) {
                            Text("Sök")
                        }
                    },
                )
            }
        }
    }
}