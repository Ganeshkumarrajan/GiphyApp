package com.anonymous.giphyapp.domain.error.repo

interface ErrorRepo {
    fun getErrorString(errorId: Int): String
    val errorsMap: Map<Int, String>
}
