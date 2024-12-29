package se.chalmers.hd.db.tags

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object TagTable: IntIdTable("tags") {
    var name = varchar("name", 32).uniqueIndex()
}