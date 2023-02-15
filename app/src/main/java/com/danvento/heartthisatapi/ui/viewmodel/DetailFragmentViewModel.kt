package com.danvento.heartthisatapi.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danvento.heartthisatapi.data.model.ArtistSong
import com.danvento.heartthisatapi.domain.GetArtistTracksUseCase
import kotlinx.coroutines.launch

class DetailFragmentViewModel(
    private val getArtistTracks: GetArtistTracksUseCase
) : ViewModel() {

    val songsModel = MutableLiveData<List<ArtistSong>>()

    fun getSongs(artistId: String) {
        viewModelScope.launch {
            val artistSongs = getArtistTracks(artistId)

            songsModel.postValue(artistSongs)

        }
    }

}