package se.chalmers.hd.db.melodies

import org.jetbrains.exposed.v1.core.dao.id.IntIdTable

object MelodyTable: IntIdTable("melodies") {
    val name = text("name")
    val url = text("url").nullable()
}