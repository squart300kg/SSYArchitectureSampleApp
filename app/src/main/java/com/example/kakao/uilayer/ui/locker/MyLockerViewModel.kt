package com.example.kakao.uilayer.ui.locker

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.kakao.datalayer.repository.ImageRepository
import com.example.kakao.uilayer.base.BaseViewModel
import com.example.kakao.uilayer.model.ItemImageUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyLockerViewModel @Inject constructor(
    private val imageRepository: ImageRepository,
) : BaseViewModel() {

    private val _uiState = MutableStateFlow<List<ItemImageUiState>>(emptyList())
    val uiState = _uiState.asStateFlow()

    fun fetchLocalImages() {
        viewModelScope.launch {
            imageRepository.localImages
                .onStart { setLoading(true) }
                .flowOn(Dispatchers.IO)
                .map { Result.success(it) }
                .catch { emit(Result.failure(it)) }
                .onCompletion { setLoading(false) }
                .collect { result ->
                    result.fold(
                        onSuccess = { receivedImages ->
                            _uiState.update { receivedImages }
                        },
                        onFailure = (::showError)
                    )
                }
        }
    }

    fun deleteImageToLocal(imageUiState: ItemImageUiState) {
        viewModelScope.launch {
            imageRepository.deleteImageToLocal(imageUiState)
                .onStart { setLoading(true) }
                .flowOn(Dispatchers.IO)
                .map { Result.success(it) }
                .catch { emit(Result.failure(it)) }
                .onCompletion { setLoading(false) }
                .collect { result ->
                    result.fold(
                        onSuccess = { deletedItem ->
                            _uiState.update { uiState ->
                                uiState.toMutableList().apply {
                                    remove(deletedItem)
                                }
                            }
                        },
                        onFailure = (::showError)
                    )
                }
        }
    }
}