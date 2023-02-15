package com.danvento.heartthisatapi.domain

import com.danvento.heartthisatapi.data.MusicRepository
import com.danvento.heartthisatapi.data.model.ArtistSong
import com.danvento.heartthisatapi.data.model.toSongList

class GetArtistTracksUseCase(
    private val musicRepository: MusicRepository
) {

    suspend operator fun invoke(artistId: String): List<ArtistSong> = musicRepository.getArtistData(artistId, "tracks").toSongList()

}