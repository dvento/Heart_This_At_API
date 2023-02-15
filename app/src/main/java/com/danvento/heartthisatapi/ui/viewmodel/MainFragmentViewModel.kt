package com.danvento.heartthisatapi.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danvento.heartthisatapi.data.MusicRepository
import com.danvento.heartthisatapi.data.model.PopularArtist
import com.danvento.heartthisatapi.di.viewModelModule
import com.danvento.heartthisatapi.domain.GetTopArtistsUseCase
import kotlinx.coroutines.launch

class MainFragmentViewModel(
    private val getTopArtistsUseCase: GetTopArtistsUseCase
) : ViewModel() {

    val artistsModel = MutableLiveData<List<PopularArtist>>()

    fun getArtists() {
        viewModelScope.launch {
            val popularArtists = getTopArtistsUseCase()

            artistsModel.postValue(popularArtists)
        }
    }

}