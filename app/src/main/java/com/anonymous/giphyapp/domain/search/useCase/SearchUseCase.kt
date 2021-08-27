package com.anonymous.giphyapp.domain.search.useCase

import com.anonymous.giphyapp.domain.search.model.AnimatedImagesInMultipleSize
import com.anonymous.giphyapp.domain.search.model.Resource
import com.anonymous.giphyapp.domain.search.model.SearchParams
import com.anonymous.giphyapp.domain.search.model.SearchType
import com.anonymous.giphyapp.domain.search.repo.SearchRepository
import kotlinx.coroutines.flow.Flow

typealias AnimatedImageSearchBaseUseCase = BaseUseCase<SearchParams, Flow<Resource<List<AnimatedImagesInMultipleSize>>>>

class AnimatedImageSearchUseCase(private val repo: SearchRepository) :
    AnimatedImageSearchBaseUseCase {
    override suspend fun invoke(parameter: SearchParams): Flow<Resource<List<AnimatedImagesInMultipleSize>>> {
        return when (parameter.searchType) {
            SearchType.NEW -> repo.getSearchList(parameter.query)
            SearchType.LOAD_MORE -> repo.loadMore(parameter.query)
        }
    }
}
