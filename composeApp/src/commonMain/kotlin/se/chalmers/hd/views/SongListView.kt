package se.chalmers.hd.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.ktor.client.plugins.*
import kotlinx.coroutines.delay
import org.jetbrains.compose.ui.tooling.preview.Preview
import se.chalmers.hd.components.SongItem
import se.chalmers.hd.dto.Melody
import se.chalmers.hd.dto.Song
import se.chalmers.hd.services.getSong
import se.chalmers.hd.services.getSongList
import se.chalmers.hd.services.searchSong

class SongListView(initialSearch: String = "") : ScreenView{
    override var rootPadding: PaddingValues = PaddingValues()
    override val topBarTitle: @Composable (() -> Unit)
        get() = { Text("Sånger") }
    private var songs: List<Song> by mutableStateOf(listOf())
    private var searchQuery by mutableStateOf(initialSearch)
    private var isLoading by mutableStateOf(true)

    @Composable
    override fun Content() {
        remember { isLoading }
        remember { songs }
        SongListEffect()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(rootPadding) // Apply padding only to the main content
        ) {
            // Always display the Search Bar at the top
            SearchBar()
            if(isLoading && songs.isEmpty()) {
                CircularProgressIndicator(Modifier.align(Alignment.CenterHorizontally))
            } else {
                SongListContent(songs)
            }
        }
    }

    @Composable
    fun SongListEffect() = LaunchedEffect(searchQuery) {
        isLoading = true
        if(songs.isNotEmpty()) {
            delay(400) // avoid spamming server while typing
        }

        var query = searchQuery.trim()
        songs = if(query.toIntOrNull() != null) {
            println("Searching for id ${query.toInt()}")
            try {
                listOf(getSong(query.toInt()))
            }catch (e: ServerResponseException) {
                e.printStackTrace()
                listOf()
            }
        } else if(query.isNotBlank()){
            println("Searching for $query")
            searchSong(query)
        } else {
            getSongList()
        }

        isLoading = false
    }

    @Composable
    fun SearchBar() {
        remember { searchQuery }
        TextField(
            value = searchQuery,
            onValueChange = { newQuery -> searchQuery = newQuery },
            label = { Text("Sök", fontSize = 16.sp) },
            placeholder = { Text("Sok efter titel, lyrik eller ID", fontSize = 13.sp) },
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 20.dp),
            singleLine = true
        )

        if(isLoading){
            LinearProgressIndicator(Modifier.fillMaxWidth())
        }
    }

    private fun filterSongs(songs: List<Song>, query: String): List<Song> {
        if (query.isBlank()) return songs // Return the full list if no query
        return songs.filter { song ->
            song.title.contains(query, ignoreCase = true) ||
                    song.melody?.name?.contains(query, ignoreCase = true) ?: false
        }
    }

    @Preview
    @Composable
    fun PreviewSongList() {
        songs = listOf(
            Song(
                id = 1, title = "Song A", chapter = "Chapter 1",
                content = "A nice song about nice things",
                ranking = 2000,
                melody = Melody(1, "test1", "")
            ),
            Song(
                id = 2, title = "Song AA", chapter = "Chapter 1",
                content = "A nice song about nice things",
                ranking = 2000,
                melody = Melody(1, "test1", "")
            ),
            Song(
                id = 3, title = "Song AAA", chapter = "Chapter 1",
                content = "A nice song about nice things",
                ranking = 2000,
                melody = Melody(1, "test1", "")
            ),
            Song(
                id = 2, title = "Song B", chapter = "Chapter 1",
                content = "Another song about nice stuff",
                ranking = 1980,
                melody = Melody(2, "test2", "")
            ),
            Song(
                id = 3, title = "Song C", chapter = "Chapter 2",
                content = "My songs are amazing",
                ranking = 1999,
                melody = Melody(1, "test1", "")
            ),
        )
        isLoading = false

        Content() // Show the mock data
    }

    @Composable
    fun SongListContent(songs: List<Song>) {
        // Group songs by chapters
        val groupedSongs = songs.groupBy { it.chapter  ?: "Övrigt"}
        Surface(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top
            ) {
                groupedSongs.forEach { (chapter, chapterSongs) ->
                    // Chapter header
                    item {
                        if (chapter != null) {
                            ChapterHeader(chapterName = chapter)
                        }
                    }
                    // Songs in the chapter
                    items(chapterSongs) { song ->
                        SongItem(song)
                    }
                }
            }
        }
    }

    @Composable
    fun ChapterHeader(chapterName: String) {
        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.Start) {
            HorizontalDivider(thickness = 4.dp)
            Text(
                text = chapterName,
                modifier = Modifier.padding(8.dp),
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
            )
        }
    }
}