package com.danvento.heartthisatapi.di

import com.danvento.heartthisatapi.core.RetrofitHelper
import com.danvento.heartthisatapi.data.MusicRepository
import com.danvento.heartthisatapi.data.network.HeartThisApiService
import com.danvento.heartthisatapi.domain.GetArtistTracksUseCase
import com.danvento.heartthisatapi.domain.GetTopArtistsUseCase
import com.danvento.heartthisatapi.ui.viewmodel.DetailFragmentViewModel
import com.danvento.heartthisatapi.ui.viewmodel.MainFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module {
    single { RetrofitHelper.provideRetrofitInstance() }
    single { RetrofitHelper.provideBankApiClient(get()) }
    single { HeartThisApiService(get()) }
    single { MusicRepository(get()) }
}

val viewModelModule = module {
    viewModel { MainFragmentViewModel(get()) }
    viewModel { DetailFragmentViewModel(get()) }
}

val useCaseModule = module {
    single { GetTopArtistsUseCase(get()) }
    single { GetArtistTracksUseCase(get()) }
}
