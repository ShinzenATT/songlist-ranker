package se.chalmers.hd.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview
import se.chalmers.hd.components.SongItem
import se.chalmers.hd.dto.Melody
import se.chalmers.hd.dto.Song

class SongListView : ScreenView{
    override var rootPadding: PaddingValues = PaddingValues()
    override val topBarTitle: @Composable (() -> Unit)
        get() = { Text("Sånger") }
    private var songs: List<Song> = listOf()
    private var searchQuery by mutableStateOf("")

    @Composable
    override fun Content() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(rootPadding) // Apply padding only to the main content
        ) {
            // Always display the Search Bar at the top
            SearchBar()

            // Show filtered songs or empty state
            if (songs.isEmpty()) {
                PreviewSongList()
            }
            var filteredSongs = filterSongs(songs, searchQuery)
            SongListContent(filteredSongs)
        }
    }

    @Composable
    fun SearchBar() {
        TextField(
            value = searchQuery,
            onValueChange = { newQuery -> searchQuery = newQuery },
            label = { Text("Filtrera", fontSize = 16.sp) },
            placeholder = { Text("Filtrera efter titel och/eller melodi", fontSize = 13.sp) },
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 20.dp),
            singleLine = true
        )
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

        SongListContent(songs) // Show the mock data
    }

    @Composable
    fun SongListContent(songs: List<Song>) {
        // Group songs by chapters
        val groupedSongs = songs.groupBy { it.chapter }
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