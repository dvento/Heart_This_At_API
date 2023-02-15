package com.danvento.heartthisatapi.data.model

// Final data model for the recycler adapter
data class PopularArtist(
    val name: String,
    val userId: String,
    val avatarUrl: String,
    val playbackCount: Int,
    val favCount: Int,
)