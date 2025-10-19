package se.chalmers.hd.db.songs

import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.IntEntity
import org.jetbrains.exposed.v1.dao.IntEntityClass
import se.chalmers.hd.db.melodies.MelodyEntity
import se.chalmers.hd.db.tags.TagEntity

class SongEntity(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<SongEntity>(SongsTable)

    var title by SongsTable.title
    var contents by SongsTable.contents
    var ranking by SongsTable.ranking
    var chapter by TagEntity optionalReferencedOn SongsTable.chapter
    var melody by MelodyEntity optionalReferencedOn SongsTable.melody
    val searchVectors by SongsTable.searchVectors
}