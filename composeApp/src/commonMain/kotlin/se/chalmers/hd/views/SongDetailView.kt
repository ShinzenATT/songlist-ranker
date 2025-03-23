package se.chalmers.hd.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import se.chalmers.hd.dto.Song
import se.chalmers.hd.services.getRandomSong

class SongDetailView(initialSong: Song?) : ScreenView {
    override var rootPadding: PaddingValues = PaddingValues(0.dp)
    override val topBarTitle: @Composable (() -> Unit)
        get() = { if(song != null) Text("${song!!.id}. ${song!!.title}") }
    var song: Song? by mutableStateOf(initialSong)

    @Composable
    override fun Content() {
        remember { song }

        if(song == null) {
            LaunchedEffect(Unit) {
                song = getRandomSong()
            }

            Column(Modifier.padding(rootPadding).fillMaxSize()) {
                CircularProgressIndicator(Modifier.align(Alignment.CenterHorizontally))
            }
        }
        else {
            Column(Modifier.padding(rootPadding).fillMaxSize().padding(16.dp).verticalScroll(rememberScrollState())) {
                Row(Modifier.fillMaxWidth().padding(bottom = 10.dp), Arrangement.SpaceBetween) {
                    if (song!!.melody != null) {
                        Text(song!!.melody!!.name)
                    }
                    Text(song!!.chapter ?: "Övrigt")
                }

                Text(song!!.content, textAlign = TextAlign.Justify)
            }
        }
    }
}