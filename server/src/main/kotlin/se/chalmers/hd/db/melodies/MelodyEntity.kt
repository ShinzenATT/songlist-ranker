package se.chalmers.hd.db.melodies

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class MelodyEntity(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<MelodyEntity>(MelodyTable)

    var name by MelodyTable.name
    var url by MelodyTable.url
}