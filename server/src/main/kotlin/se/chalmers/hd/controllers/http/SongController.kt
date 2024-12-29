package se.chalmers.hd.controllers.http

import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import se.chalmers.hd.services.updateOrCreateSong
import se.chalmers.hd.db.songs.SongEntity
import se.chalmers.hd.dto.Song
import se.chalmers.hd.utils.mappers.toSongData
import kotlin.random.Random

@RestController
class SongController {

    @GetMapping("/songs")
    suspend fun getAllSongs() = suspendedTransactionAsync {
        return@suspendedTransactionAsync SongEntity.all().map { it.toSongData() }.toList()
    }.await()


    @GetMapping("/songs/{id}")
    suspend fun getSongById(@PathVariable("id") song: Int) = suspendedTransactionAsync {
        return@suspendedTransactionAsync SongEntity.findById(song)?.toSongData() ?: throw NoSuchElementException("Song with id $song does not exist")
    }.await()

    @GetMapping("/songs/random")
    suspend fun getRandomSong() = suspendedTransactionAsync {
        val total = SongEntity.count()
        val random = Random.nextLong(total) + 1
        SongEntity.findById(random.toInt())?.toSongData() ?: throw NoSuchElementException("No song with id $random")
    }.await()

    @PostMapping("/songs")
    suspend fun submitSong(@RequestBody song: Song) = suspendedTransactionAsync {
        return@suspendedTransactionAsync updateOrCreateSong(song).toSongData()
    }.await()
}