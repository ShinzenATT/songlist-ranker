package se.chalmers.hd.dto.requests

import kotlinx.serialization.Serializable

@Serializable
data class SearchRequest(val query: String)
