package com.anonymous.giphyapp.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anonymous.giphyapp.domain.error.usecase.ErrorBaseUseCase
import com.anonymous.giphyapp.domain.search.model.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class BaseViewModel(open val errorUseCase: ErrorBaseUseCase) : ViewModel() {
    protected fun  launchCoroutine(
        block: suspend CoroutineScope.() -> Unit,
    ): Job {
        return viewModelScope.launch {
            block()
        }
    }

    fun getErrorMessage(errorCode: Int) = errorUseCase.getError(errorCode)
}
