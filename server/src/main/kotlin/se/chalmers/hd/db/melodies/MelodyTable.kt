package se.chalmers.hd.db.melodies

import org.jetbrains.exposed.dao.id.IntIdTable

object MelodyTable: IntIdTable("melodies") {
    val name = text("name")
    val url = text("url").nullable()
}