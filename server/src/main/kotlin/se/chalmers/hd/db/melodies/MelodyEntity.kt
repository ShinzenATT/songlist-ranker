package se.chalmers.hd.db.melodies

import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.IntEntity
import org.jetbrains.exposed.v1.dao.IntEntityClass

class MelodyEntity(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<MelodyEntity>(MelodyTable)

    var name by MelodyTable.name
    var url by MelodyTable.url
}