package se.chalmers.hd.components

import androidx.compose.foundation.layout.Column
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
        Column(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp, horizontal = 10.dp)
        ) {
            ElevatedCard(
                modifier = Modifier.fillMaxWidth()
            ){
                Text(text = song.title, fontSize = 16.sp)
            }
        }
}