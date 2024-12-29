package se.chalmers.hd.db.tags

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID

class TagEntity(name: String): Entity<String>(EntityID(name, TagTable)) {   
    companion object: EntityClass<String, TagEntity>(TagTable)
    var name by TagTable.name
}