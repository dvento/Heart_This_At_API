package com.danvento.heartthisatapi.data

import android.util.Log
import com.danvento.heartthisatapi.data.model.ApiResponse
import com.danvento.heartthisatapi.data.model.Track
import com.danvento.heartthisatapi.data.network.HeartThisApiService

/*
 For a more complex use of the API, we could build
 two services, one for artists, the other for tracks,
 but there's no need for now
 */

class MusicRepository(
    private val apiService: HeartThisApiService
) {

    companion object {
        private val TAG = Companion::class.java.canonicalName
    }

    /*
    In the future, here will be able to manage the data sources,
    being capable of handling offline music playback
     */

    // TODO: better handling of errors
    suspend fun getTrackList(type: String?): List<Track> {
        val serviceResponse = apiService.getTrackList(type)

        return manageResponse(serviceResponse)
    }

    suspend fun getArtistData(artistId: String, type: String?): List<Track> {
        val serviceResponse = apiService.getArtistData(artistId, type)

        return manageResponse(serviceResponse)
    }


    private fun manageResponse(response: ApiResponse<List<Track>>): List<Track> {
        return if (response.data != null) {
            response.data
        } else {
            Log.e(TAG, response.message!!)
            emptyList()
        }
    }
}