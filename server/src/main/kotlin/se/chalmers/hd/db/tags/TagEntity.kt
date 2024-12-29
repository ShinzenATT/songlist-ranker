package se.chalmers.hd.db.tags

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class TagEntity(id: EntityID<Int>): IntEntity(id) {
    companion object: IntEntityClass<TagEntity>(TagTable)
    var name by TagTable.name
}