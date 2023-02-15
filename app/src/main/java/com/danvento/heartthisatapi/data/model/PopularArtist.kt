package com.danvento.heartthisatapi.data.model

data class PopularArtist(
    val name: String,
    val userId: String,
    val avatarUrl: String,
    val playbackCount: Int,
    val favCount: Int,
)