package se.chalmers.hd.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen

interface ScreenView: Screen {
    val topBarTitle: @Composable (() -> Unit)
        get() = { Text("HD-Sektionens Sånglista") }
    val topBarVisible: Boolean get() = true
    var rootPadding: PaddingValues
    val topBarActions: @Composable (RowScope.() -> Unit)
        get() = {}
    var nestedScrollConnection: NestedScrollConnection?

    companion object {
        private class ScreenWrapper(
            val screen: Screen,
            override var nestedScrollConnection: NestedScrollConnection?
        ) : ScreenView {
            override var rootPadding: PaddingValues = PaddingValues(0.dp)

            @Composable
            override fun Content() = screen.Content()
        }
        fun default(screen: Screen): ScreenView = ScreenWrapper(screen, null)
    }
}

fun Screen.asScreenView(): ScreenView{
    if(this is ScreenView){ return this }
    return ScreenView.default(this)
}