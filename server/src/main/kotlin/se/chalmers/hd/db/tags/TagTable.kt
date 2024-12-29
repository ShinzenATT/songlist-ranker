package se.chalmers.hd.db.tags

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column

object TagTable: IdTable<String>("tags") {
    var name = varchar("name", 32).entityId()
    override val id: Column<EntityID<String>>
        get() = name
}