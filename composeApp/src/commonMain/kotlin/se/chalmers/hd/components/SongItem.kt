package se.chalmers.hd.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import se.chalmers.hd.dto.Song


@Composable
fun SongItem(song: Song){

    val songId = song.id

        Column(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 10.dp)
        ) {
            ElevatedCard(
                modifier = Modifier.fillMaxWidth(),
                ///TODO: Add onClick navigate to songId
            ){
                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(text = song.title, fontSize = 16.sp, modifier = Modifier.padding(start = 12.dp))
                        song.melody?.let { Text(text = it.name, fontSize = 13.sp, modifier = Modifier.padding(start = 20.dp, bottom = 2.dp)) }
                    }
                }
            }
        }
}