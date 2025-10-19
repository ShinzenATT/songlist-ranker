package se.chalmers.hd.db.songs

import org.jetbrains.exposed.v1.core.dao.id.IntIdTable
import se.chalmers.hd.db.configuration.tsVector
import se.chalmers.hd.db.melodies.MelodyTable
import se.chalmers.hd.db.tags.TagTable

object SongsTable: IntIdTable("songs") {
    val title = text("title")
    val melody = optReference("melody", MelodyTable.id)
    val contents = text("contents")
    val ranking = integer("ranking").default(1000)
    val chapter = optReference("chapter", TagTable.id)
    val searchVectors = tsVector("search_vectors")
}