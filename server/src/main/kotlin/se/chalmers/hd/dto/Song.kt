package se.chalmers.hd.dto

data class Song(val id: Int, val title: String, val content: String, val ranking: Int, val chapter: String?, val melody: Melody?)
