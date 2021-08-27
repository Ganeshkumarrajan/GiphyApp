package com.anonymous.giphyapp

import android.app.Application
import com.anonymous.giphyapp.di.RepositoryModule
import com.anonymous.giphyapp.di.UseCaseModule
import com.anonymous.giphyapp.di.networkModule
import com.anonymous.giphyapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GiphyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@GiphyApp)
            modules(
                networkModule,
                RepositoryModule,
                UseCaseModule,
                viewModelModule
            )
        }
    }
}
