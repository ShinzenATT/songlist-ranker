package se.chalmers.hd.utils.mappers

import se.chalmers.hd.db.melodies.MelodyEntity
import se.chalmers.hd.db.songs.SongEntity
import se.chalmers.hd.dto.Melody
import se.chalmers.hd.dto.Song

fun SongEntity.toSongData(): Song = Song(
    this.id.value,
    this.title,
    this.contents,
    this.ranking,
    this.chapter?.name,
    this.melody?.toMelodyData()
)

fun MelodyEntity.toMelodyData() = Melody(
    this.id.value,
    this.name,
    this.url
)

fun SongEntity.update(source: Song): SongEntity {
    this.title = source.title
    this.contents = source.content
    this.ranking = source.ranking
    return this
}

fun MelodyEntity.update(source: Melody): MelodyEntity {
    this.name = source.name
    this.url = source.url
    return this
}
