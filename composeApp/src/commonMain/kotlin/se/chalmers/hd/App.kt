package se.chalmers.hd

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import org.jetbrains.compose.ui.tooling.preview.Preview
import se.chalmers.hd.models.AppState
import se.chalmers.hd.views.HomeView
import se.chalmers.hd.views.ViewOne
import se.chalmers.hd.views.ViewTwo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    Navigator(HomeView()) { navigator ->
        MaterialTheme {
            Scaffold(
                topBar = { TopAppBar(title = {Text("HDs sångbok")}) },
                content = {
                    AppState.paddingValueState.value = it
                    CurrentScreen()
                  },
                bottomBar = { NavigationBar {
                    NavigationBarItem(
                        icon = { Icon(imageVector = Icons.Default.Home, contentDescription = null) },
                        selected = navigator.lastItem is HomeView,
                        label = { Text("Home") },
                        onClick = {
                            navigator.popUntilRoot()
                            navigator.push(HomeView())
                        },
                    )
                    NavigationBarItem(
                        icon = { Icon(imageVector = Icons.Default.Search, contentDescription = null) },
                        selected = navigator.lastItem is ViewOne,
                        label = { Text("View One") },
                        onClick = {
                            navigator.popUntilRoot()
                            navigator.push(ViewOne())
                        },
                    )
                    NavigationBarItem(
                        icon = { Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null) },
                        selected = navigator.lastItem is ViewTwo,
                        label = { Text("View Two") },
                        onClick = {
                            navigator.popUntilRoot()
                            navigator.push(ViewTwo())
                        },
                    )
                } }
            )
        }
    }
}