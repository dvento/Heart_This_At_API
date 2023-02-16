package com.danvento.heartthisatapi.data.network

import com.danvento.heartthisatapi.data.model.ApiResponse
import com.danvento.heartthisatapi.data.model.Track
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/*
 For a more complex use of the API, we could build
 two services, one for artists, the other for tracks,
 but there's no need for now
 */

class HeartThisApiService(
    private val heartThisApiClient: HeartThisApiClient
) {
    /**
     *  TODO: better handling of errors,
     *  show error info to users
     */


    suspend fun getTrackList(type: String?): ApiResponse<List<Track>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = heartThisApiClient.getFeed(type)

                if (response.code() == 200) {
                    ApiResponse.Success(data = response.body()!!)
                } else {
                    ApiResponse.Error(message = "Api error while getting the tracks. Error code: ${response.code()}")
                }
            } catch (e: Exception) {
                ApiResponse.Error(message = "There was an exception while getting the tracks. Error code: ${e.message}")
            }
        }
    }

    suspend fun getArtistData(artistId: String, type: String?): ApiResponse<List<Track>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = heartThisApiClient.getArtistData(artistId, type)

                if (response.code() == 200) {
                    ApiResponse.Success(data = response.body()!!)
                } else {
                    ApiResponse.Error(message = "Api error while getting artist info. ArtistID: $artistId. Type : $type. Error code: ${response.code()}")
                }
            } catch (e: Exception) {
                ApiResponse.Error(message = "There was an exception while getting artist info. ArtistID: $artistId . Type : $type. Error code: ${e.message}")
            }
        }
    }

}