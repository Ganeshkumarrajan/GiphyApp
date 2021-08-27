package com.anonymous.giphyapp.di

import com.anonymous.giphyapp.domain.error.repo.ErrorRepo
import com.anonymous.giphyapp.domain.error.usecase.ErrorBaseUseCase
import com.anonymous.giphyapp.domain.error.usecase.ErrorUseCase
import com.anonymous.giphyapp.domain.search.repo.SearchRepository
import com.anonymous.giphyapp.domain.search.useCase.AnimatedImageSearchBaseUseCase
import com.anonymous.giphyapp.domain.search.useCase.AnimatedImageSearchUseCase
import org.koin.dsl.module

val UseCaseModule = module {
    single {
        provideSearchUseCase(get())
    }

    single {
        provideErrorUseCase(get())
    }
}

fun provideSearchUseCase(searchRepository: SearchRepository): AnimatedImageSearchBaseUseCase {
    return AnimatedImageSearchUseCase(searchRepository)
}

fun provideErrorUseCase(repo: ErrorRepo): ErrorBaseUseCase {
    return ErrorUseCase(repo)
}
