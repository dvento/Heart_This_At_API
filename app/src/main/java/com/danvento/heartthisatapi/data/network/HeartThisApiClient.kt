package com.danvento.heartthisatapi.data.network

import com.danvento.heartthisatapi.data.ApiConstants
import com.danvento.heartthisatapi.data.model.TrackList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HeartThisApiClient {

    // page and count params omitted for simplicity and diversity of tracks
    @GET(ApiConstants.FEED)
    suspend fun getFeed(@Query("type") feedType: String?): Response<TrackList>

    @GET("{id}")
    suspend fun getArtistData(
        @Path("id") artistId: String,
        @Query("type") feedType: String?
    ): Response<TrackList>
}