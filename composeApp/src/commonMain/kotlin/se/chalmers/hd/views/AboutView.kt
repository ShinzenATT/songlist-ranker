package se.chalmers.hd.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.platform.LocalUriHandler
import cafe.adriel.voyager.navigator.LocalNavigator

class AboutView: ScreenView {
    override var rootPadding: PaddingValues = PaddingValues()
    override var nestedScrollConnection: NestedScrollConnection? = null
    override val topBarTitle: @Composable (() -> Unit)
        get() = { Text("Om Sångboken") }

    @Composable
    override fun Content() {
        var taps by remember { mutableStateOf(0) }
        val navigator = LocalNavigator.current
        val uriHandler = LocalUriHandler.current

        LaunchedEffect(taps) {
            if(taps > 10){
                navigator?.push(SubmitSongView())
            }
        }

        Column(Modifier.padding(rootPadding)) {
            ListItem(
                modifier = Modifier.clickable { taps++ },
                headlineContent = { Text("Developed by") },
                supportingContent = { Text("ShinzenATT & Sinestral") }
            )
            ListItem(
                modifier = Modifier.clickable { uriHandler.openUri("https://github.com/ShinzenATT/songlist-ranker") },
                headlineContent = { Text("See the project on Github") },
                supportingContent = { Text("ShinzenATT/songlist-ranker") },
                trailingContent = { Icon(Icons.Default.Star, "Open Github Repository") }
            )
        }
    }
}