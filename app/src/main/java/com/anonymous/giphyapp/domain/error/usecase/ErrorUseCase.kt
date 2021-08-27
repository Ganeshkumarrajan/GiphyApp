package com.anonymous.giphyapp.domain.error.usecase

import com.anonymous.giphyapp.domain.error.model.ErrorModel
import com.anonymous.giphyapp.domain.error.repo.ErrorRepo

interface ErrorBaseUseCase {
    fun getError(errorCode: Int): ErrorModel
}

class ErrorUseCase(private val errorMapper: ErrorRepo) : ErrorBaseUseCase {
    override fun getError(errorCode: Int): ErrorModel {
        return ErrorModel(
            description = errorMapper.errorsMap.getValue(errorCode)
        )
    }
}
