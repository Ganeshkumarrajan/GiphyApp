package com.anonymous.giphyapp.di

import com.anonymous.giphyapp.ui.gifSearch.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SearchViewModel(get(), get()) }
}
