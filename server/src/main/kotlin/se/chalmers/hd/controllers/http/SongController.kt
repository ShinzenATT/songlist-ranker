package se.chalmers.hd.controllers.http

import org.jetbrains.exposed.v1.jdbc.transactions.experimental.suspendedTransactionAsync
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import se.chalmers.hd.configuration.AppConfig
import se.chalmers.hd.db.songs.SongEntity
import se.chalmers.hd.dto.Song
import se.chalmers.hd.dto.requests.SearchRequest
import se.chalmers.hd.services.searchSongs
import se.chalmers.hd.services.updateOrCreateSong
import se.chalmers.hd.utils.mappers.toSongData
import kotlin.random.Random

@RestController
class SongController(val config: AppConfig) {

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
    suspend fun submitSong(
        @RequestHeader(HttpHeaders.AUTHORIZATION) auth: String,
        @RequestBody song: Song
    ): ResponseEntity<Song> {
        if(!config.checkCredentials(auth)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).header(HttpHeaders.WWW_AUTHENTICATE, "Basic").build<Song>()
        }

        val res = suspendedTransactionAsync {
            return@suspendedTransactionAsync updateOrCreateSong(song).toSongData()
        }.await()

        return ResponseEntity.accepted().body(res)
    }

    @PostMapping("/songs/search")
    suspend fun searchSong(@RequestBody request: SearchRequest) = suspendedTransactionAsync {
        return@suspendedTransactionAsync searchSongs(request.query).map { it.toSongData() }.toList()
    }.await()
}