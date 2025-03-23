package se.chalmers.hd.dto

import kotlinx.serialization.Serializable

@Serializable
data class Melody(val id: Int?, val name: String, val url: String?)
