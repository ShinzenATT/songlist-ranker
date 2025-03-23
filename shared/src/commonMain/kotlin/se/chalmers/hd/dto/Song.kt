package se.chalmers.hd.dto

import kotlinx.serialization.Serializable

@Serializable
data class Song(val id: Int?, val title: String, val content: String, val ranking: Int = 1000, val chapter: String?, val melody: Melody?)
