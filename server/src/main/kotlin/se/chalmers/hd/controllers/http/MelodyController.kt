package se.chalmers.hd.controllers.http

import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import se.chalmers.hd.configuration.AppConfig
import se.chalmers.hd.db.melodies.MelodyEntity
import se.chalmers.hd.dto.Melody
import se.chalmers.hd.services.updateOrCreateMelody
import se.chalmers.hd.utils.mappers.toMelodyData

@RestController
class MelodyController(val config: AppConfig) {

    @GetMapping("/melodies")
    suspend fun getAllMelodies() = suspendedTransactionAsync {
        return@suspendedTransactionAsync MelodyEntity.all().map { it.toMelodyData() }.toList()
    }.await()


    @GetMapping("/melodies/{id}")
    suspend fun getMelodyById(@PathVariable("id") melody: Int) = suspendedTransactionAsync {
        return@suspendedTransactionAsync MelodyEntity.findById(melody)?.toMelodyData() ?: throw NoSuchElementException("Melody with id $melody does not exist")
    }.await()

    @PostMapping("/melodies")
    suspend fun submitMelody(
        @RequestHeader(HttpHeaders.AUTHORIZATION) auth: String,
        @RequestBody melody: Melody
    ): ResponseEntity<Melody> {
        if(!config.checkCredentials(auth)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).header(HttpHeaders.WWW_AUTHENTICATE, "Basic").build<Melody>()
        }

        val res = suspendedTransactionAsync {
            return@suspendedTransactionAsync updateOrCreateMelody(melody).toMelodyData()
        }.await()
        return ResponseEntity.accepted().body(res)
    }

}