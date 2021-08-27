package com.anonymous.giphyapp.domain.search.useCase

interface BaseUseCase<in Parameter, out Result> {
    suspend operator fun invoke(parameter: Parameter): Result
}
