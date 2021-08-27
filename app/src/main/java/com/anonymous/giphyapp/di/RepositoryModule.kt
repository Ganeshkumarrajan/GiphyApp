package com.anonymous.giphyapp.di

import com.anonymous.giphyapp.data.repository.ErrorRepoImpl
import com.anonymous.giphyapp.data.repository.SearchRepositoryImpl
import com.anonymous.giphyapp.domain.error.repo.ErrorRepo
import com.anonymous.giphyapp.domain.search.repo.SearchRepository
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val RepositoryModule = module {
    single<SearchRepository> {
        SearchRepositoryImpl(get(), Dispatchers.IO)
    }

    single<ErrorRepo> {
        ErrorRepoImpl(androidContext())
    }
}
