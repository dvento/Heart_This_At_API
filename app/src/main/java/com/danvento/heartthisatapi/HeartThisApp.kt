package com.danvento.heartthisatapi

import android.app.Application
import com.danvento.heartthisatapi.di.applicationModule
import com.danvento.heartthisatapi.di.useCaseModule
import com.danvento.heartthisatapi.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class HeartThisApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@HeartThisApp)
            modules(
                listOf(
                    applicationModule,
                    viewModelModule,
                    useCaseModule
                )
            )
        }

    }
}