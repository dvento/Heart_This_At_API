package com.danvento.heartthisatapi.core

import com.danvento.heartthisatapi.data.network.HeartThisApiClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private const val BASE_URL = "https://api-v2.hearthis.at/"

    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun provideBankApiClient(retrofit: Retrofit): HeartThisApiClient {
        return retrofit.create(HeartThisApiClient::class.java)
    }

}