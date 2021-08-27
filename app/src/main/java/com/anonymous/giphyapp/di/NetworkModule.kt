package com.anonymous.giphyapp.di

import com.anonymous.giphyapp.data.service.GiphyService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { provideTaxiApiService(retrofit = get()) }
    single { provideRetrofit(okHttpClient = get(), url = "https://api.giphy.com/v1/") }
    single { provideOkHttpClient() }
}

const val SECONDS = 60L

internal fun provideOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    return OkHttpClient.Builder()
        .connectTimeout(SECONDS, TimeUnit.SECONDS)
        .readTimeout(SECONDS, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .build()
}

internal fun provideRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}

internal fun provideTaxiApiService(retrofit: Retrofit): GiphyService =
    retrofit.create(GiphyService::class.java)
