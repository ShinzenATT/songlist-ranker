package se.chalmers.hd.db.tags

import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.IntEntity
import org.jetbrains.exposed.v1.dao.IntEntityClass

class TagEntity(id: EntityID<Int>): IntEntity(id) {
    companion object: IntEntityClass<TagEntity>(TagTable)
    var name by TagTable.name
}