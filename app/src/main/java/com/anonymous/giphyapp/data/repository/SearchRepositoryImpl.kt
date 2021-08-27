package com.anonymous.giphyapp.data.repository

import com.anonymous.giphyapp.data.GifImagesRemoteResponse
import com.anonymous.giphyapp.data.service.GiphyService
import com.anonymous.giphyapp.data.toDomain
import com.anonymous.giphyapp.domain.error.model.NO_INTERNET_CONNECTION
import com.anonymous.giphyapp.domain.error.model.SEARCH_ERROR
import com.anonymous.giphyapp.domain.search.model.AnimatedImagesInMultipleSize
import com.anonymous.giphyapp.domain.search.model.Resource
import com.anonymous.giphyapp.domain.search.repo.SearchRepository
import com.bumptech.glide.load.HttpException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import kotlin.coroutines.CoroutineContext

class SearchRepositoryImpl(
    private val giphyService: GiphyService,
    private val ioDispatcher: CoroutineContext
) :
    SearchRepository {

    private val tempSearchListCache = mutableListOf<AnimatedImagesInMultipleSize>()

    override suspend fun getSearchList(searchParams: String): Flow<Resource<List<AnimatedImagesInMultipleSize>>> {
        clearTempList()
        return requestAndSave(searchParams)
    }

    override suspend fun loadMore(searchParams: String): Flow<Resource<List<AnimatedImagesInMultipleSize>>> =
        requestAndSave(searchParams)

    private suspend fun requestAndSave(query: String): Flow<Resource<List<AnimatedImagesInMultipleSize>>> =
        flow {
            try {
                val endPointResult = doRequest(query)
                emit(parseAndSaveEndPointResponse(endPointResult))
            } catch (exception: IOException) {
                emit(Resource.DataError<List<AnimatedImagesInMultipleSize>>(SEARCH_ERROR))
            } catch (exception: HttpException) {
                emit(Resource.DataError<List<AnimatedImagesInMultipleSize>>(NO_INTERNET_CONNECTION))
            }
        }.flowOn(ioDispatcher)

    private suspend fun doRequest(query: String): GifImagesRemoteResponse =
        giphyService.fetchAnimatedImages(
            query = query,
            limit = NETWORK_PAGE_SIZE,
            offset = (tempSearchListCache.size + 1)
        )

    private fun parseAndSaveEndPointResponse(response: GifImagesRemoteResponse):
            Resource<List<AnimatedImagesInMultipleSize>> =
        if (response.meta.status == SUCCESS_STATUS_CODE) {
            if (response.data.isEmpty()) {
                Resource.DataError(SEARCH_ERROR)
            } else {
                addIntoTempList(response.toDomain())
                Resource.Success(tempSearchListCache)
            }
        } else {
            Resource.DataError(SEARCH_ERROR)
        }

    private fun addIntoTempList(_data: List<AnimatedImagesInMultipleSize>) {
        tempSearchListCache.addAll(_data)
    }

    private fun clearTempList() {
        tempSearchListCache.clear()
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 20
        const val SUCCESS_STATUS_CODE = 200
    }
}
