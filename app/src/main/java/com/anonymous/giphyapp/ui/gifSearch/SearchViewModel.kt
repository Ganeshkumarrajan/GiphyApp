package com.anonymous.giphyapp.ui.gifSearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anonymous.giphyapp.domain.error.model.EMPTY_SEARCH
import com.anonymous.giphyapp.domain.error.model.SAME_QUERY_SEARCH
import com.anonymous.giphyapp.domain.error.usecase.ErrorBaseUseCase
import com.anonymous.giphyapp.domain.search.model.AnimatedImagesInMultipleSize
import com.anonymous.giphyapp.domain.search.model.Resource
import com.anonymous.giphyapp.domain.search.model.SearchParams
import com.anonymous.giphyapp.domain.search.model.SearchType
import com.anonymous.giphyapp.domain.search.useCase.AnimatedImageSearchBaseUseCase
import com.anonymous.giphyapp.ui.base.BaseViewModel
import kotlinx.coroutines.flow.collect

class SearchViewModel(
    val useCase: AnimatedImageSearchBaseUseCase,
    override val errorUseCase: ErrorBaseUseCase
) : BaseViewModel(errorUseCase) {
    private val _imageList = MutableLiveData<Resource<List<AnimatedImagesInMultipleSize>>>()
    var imageList: LiveData<Resource<List<AnimatedImagesInMultipleSize>>> = _imageList
    private var searchQuery = ""

    fun searchImages(query: String) {
        if (validateSearchQuery(query)) {
            setSearchQueryValue(query)
            doSearch()
        }
    }

    fun getMoreImages() {
       launchCoroutine {
            useCase(SearchParams(query = searchQuery, SearchType.LOAD_MORE)).collect {
                _imageList.value = it
            }
        }
    }

    private fun doSearch() {
        launchCoroutine {
            useCase(SearchParams(query = searchQuery, SearchType.NEW)).collect {
                _imageList.value = it
            }
        }
    }

    private fun validateSearchQuery(query: String): Boolean {
        return when {
            query.isEmpty() -> {
                _imageList.value = Resource.DataError(EMPTY_SEARCH)
                false
            }
            query == searchQuery -> {
                _imageList.value = Resource.DataError(SAME_QUERY_SEARCH)
                false
            }
            else -> true
        }
    }

    private fun setSearchQueryValue(query: String) {
        searchQuery = query
    }
}
