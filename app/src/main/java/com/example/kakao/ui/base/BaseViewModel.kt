package com.example.kakao.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor(): ViewModel() {

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    protected fun showError(exception: Throwable) {
        _errorMessage.value = exception.message
        _errorMessage.value = null
    }

    protected fun <T> Flow<T>.setBaseIntermediates(): Flow<Result<T>> {
        return onStart { _isLoading.value = true }
            .flowOn(Dispatchers.IO)
            .map { Result.success(it) }
            .catch { emit(Result.failure(it)) }
            .onCompletion { _isLoading.value = false }
    }
}