package com.anonymous.giphyapp.data.repository

import android.content.Context
import com.anonymous.giphyapp.R
import com.anonymous.giphyapp.domain.error.model.EMPTY_SEARCH
import com.anonymous.giphyapp.domain.error.model.NETWORK_ERROR
import com.anonymous.giphyapp.domain.error.model.NO_INTERNET_CONNECTION
import com.anonymous.giphyapp.domain.error.model.SEARCH_ERROR
import com.anonymous.giphyapp.domain.error.repo.ErrorRepo

class ErrorRepoImpl(private val context: Context) : ErrorRepo {

    override fun getErrorString(errorId: Int): String {
        return context.getString(errorId)
    }

    override val errorsMap: Map<Int, String>
        get() = mapOf(
            Pair(NO_INTERNET_CONNECTION, getErrorString(R.string.network_error)),
            Pair(NETWORK_ERROR, getErrorString(R.string.network_error)),
            Pair(EMPTY_SEARCH, getErrorString(R.string.empty_search)),
            Pair(SEARCH_ERROR, getErrorString(R.string.search_error)),
        ).withDefault { getErrorString(R.string.search_error) }
}
