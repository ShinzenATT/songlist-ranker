package se.chalmers.hd.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection

class SubmitSongView: ScreenView {
    override var rootPadding: PaddingValues = PaddingValues()
    override var nestedScrollConnection: NestedScrollConnection? = null
    override val topBarTitle: @Composable (() -> Unit)
        get() = { Text("Spara sånger") }

    @Composable
    override fun Content() {
        Column(Modifier.padding(rootPadding).fillMaxSize()) {
            Text("Submit")
        }
    }
}