package com.anonymous.giphyapp.domain.search.repo

import com.anonymous.giphyapp.domain.search.model.AnimatedImagesInMultipleSize
import com.anonymous.giphyapp.domain.search.model.Resource
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun getSearchList(searchParams: String): Flow<Resource<List<AnimatedImagesInMultipleSize>>>
    suspend fun loadMore(searchParams: String): Flow<Resource<List<AnimatedImagesInMultipleSize>>>
}
