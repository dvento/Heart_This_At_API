package com.danvento.heartthisatapi.domain

import com.danvento.heartthisatapi.data.MusicRepository
import com.danvento.heartthisatapi.data.model.PopularArtist
import com.danvento.heartthisatapi.data.model.toArtistList

class GetTopArtistsUseCase(
    private val musicRepository: MusicRepository
) {

    suspend operator fun invoke(): List<PopularArtist> =
        musicRepository.getTrackList("popular").toArtistList()
            .sortedByDescending { it.playbackCount }

}