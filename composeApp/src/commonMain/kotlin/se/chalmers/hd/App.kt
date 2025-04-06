package se.chalmers.hd

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import org.jetbrains.compose.ui.tooling.preview.Preview
import se.chalmers.hd.theme.AppTheme
import se.chalmers.hd.views.HomeView
import se.chalmers.hd.views.SongListView
import se.chalmers.hd.views.asScreenView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App(colorScheme: ColorScheme? = null) {
    AppTheme(dynamicColorTheme = colorScheme) {
        Navigator(HomeView()) { navigator ->
            val currentPage = navigator.lastItem.asScreenView()
            MaterialTheme {
                Scaffold(
                    topBar = {
                        if (currentPage.topBarVisible) {
                            TopAppBar(
                                title = { currentPage.topBarTitle() },
                                navigationIcon = {
                                    if (navigator.lastItem is HomeView) return@TopAppBar
                                    IconButton(onClick = { navigator.pop() }) {
                                        Icon(
                                            Icons.AutoMirrored.Filled.ArrowBack,
                                            contentDescription = "Back"
                                        )
                                    }
                                }
                            )
                        }
                    },
                    content = {
                        currentPage.rootPadding = it
                        CurrentScreen()
                    },
                    bottomBar = {
                        NavigationBar {
                            NavigationBarItem(
                                icon = { Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null) },
                                selected = navigator.lastItem is HomeView,
                                label = { Text("Slå Up") },
                                onClick = {
                                    navigator.popUntilRoot()
                                },
                            )
                            NavigationBarItem(
                                icon = {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.List,
                                        contentDescription = null
                                    )
                                },
                                selected = navigator.lastItem is SongListView,
                                label = { Text("Bläddra") },
                                onClick = {
                                    navigator.popUntilRoot()
                                    navigator.push(SongListView())
                                },
                            )
                        }
                    }
                )
            }
        }
    }
}