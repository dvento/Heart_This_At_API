package com.danvento.heartthisatapi.data.model

data class ArtistSong (
    val title: String,
    val artworkUrl: String,
    val bpm: String,
    val description: String,
    val duration: Int,
    val genre: String,
    val releaseDate: Int,
    val streamUrl: String,
)
