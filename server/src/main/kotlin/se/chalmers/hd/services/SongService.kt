package se.chalmers.hd.services

import org.jetbrains.exposed.sql.insert
import se.chalmers.hd.db.melodies.MelodyEntity
import se.chalmers.hd.db.melodies.MelodyTable
import se.chalmers.hd.db.songs.SongEntity
import se.chalmers.hd.db.songs.SongsTable
import se.chalmers.hd.db.tags.TagEntity
import se.chalmers.hd.db.tags.TagTable
import se.chalmers.hd.dto.Melody
import se.chalmers.hd.dto.Song
import se.chalmers.hd.utils.mappers.update

fun updateOrCreateMelody(melody: Melody):MelodyEntity {
    val result = MelodyEntity.find { MelodyTable.id eq melody.id }.firstOrNull()
    if (result != null) {
        return result.update(melody)
    }
    return MelodyEntity.new { this.update(melody) }
}

fun getOrCreateTag(tag: String): TagEntity {
    val result = TagEntity.find { TagTable.name eq tag}.firstOrNull()
    if (result != null) return result
    return TagEntity.new { name = tag }
}

fun updateOrCreateSong(song: Song):SongEntity {
    val result = SongEntity.find { SongsTable.id eq song.id }.firstOrNull()
    val melody = song.melody?.let { updateOrCreateMelody(it) }
    val chapter = song.chapter?.let { getOrCreateTag(it) }

    if (result != null) {
        result.update(song)
        result.chapter = chapter
        result.melody = melody
        return result
    }

    return SongEntity.new {
        this.update(song)
        this.chapter = chapter
        this.melody = melody
    }
}