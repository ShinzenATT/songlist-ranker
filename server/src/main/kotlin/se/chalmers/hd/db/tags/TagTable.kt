package se.chalmers.hd.db.tags

import org.jetbrains.exposed.v1.core.dao.id.IntIdTable

object TagTable: IntIdTable("tags") {
    var name = varchar("name", 32).uniqueIndex()
}