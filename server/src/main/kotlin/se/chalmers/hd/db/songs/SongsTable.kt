package se.chalmers.hd.db.songs

import org.jetbrains.exposed.dao.id.IntIdTable
import se.chalmers.hd.db.melodies.MelodyTable
import se.chalmers.hd.db.tags.TagTable

object SongsTable: IntIdTable("songs") {
    val title = text("title")
    val melody = optReference("melody", MelodyTable.id)
    val contents = text("contents")
    val ranking = integer("ranking").default(1000)
    val chapter = optReference("chapter", TagTable.name)
}