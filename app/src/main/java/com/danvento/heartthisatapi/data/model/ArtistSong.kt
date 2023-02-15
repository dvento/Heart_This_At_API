package com.danvento.heartthisatapi.data.model

// Final data model for the recycler adapter
data class ArtistSong(
    val title: String,
    val artworkUrl: String,
    val bpm: String,
    val description: String,
    val duration: Int,
    val genre: String,
    val releaseDate: Int,
    val streamUrl: String,
)
