package se.chalmers.hd.services

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import se.chalmers.hd.dto.Melody
import se.chalmers.hd.dto.Song
import se.chalmers.hd.dto.requests.SearchRequest

private val client = HttpClient() {
    expectSuccess = true
    install(Logging)
    install(ContentNegotiation){
        json(Json { ignoreUnknownKeys = true })
    }
}
private const val url = "http://10.0.2.2:8080"

suspend fun getSongList(): List<Song>  = client.get("$url/songs").body()

suspend fun getSong(id: Int): Song = client.get("$url/songs/$id").body()

suspend fun getRandomSong(): Song = client.get("$url/songs/random").body()

suspend fun searchSong(query: String): List<Song> = client.post("$url/songs/search"){
    setBody(SearchRequest(query))
    contentType(ContentType.Application.Json)
}.body()

suspend fun getMelodies(): List<Melody> = client.get("$url/melodies").body()

suspend fun getMelody(id: String): Melody = client.get("$url/melody/$id").body()
