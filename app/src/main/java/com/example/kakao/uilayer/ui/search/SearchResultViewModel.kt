package com.example.kakao.uilayer.ui.search

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.kakao.datalayer.repository.ImageRepository
import com.example.kakao.domainlayer.GetHomeImagesWithCheckedUseCase
import com.example.kakao.uilayer.base.BaseViewModel
import com.example.kakao.uilayer.model.ItemImageUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(
    private val getHomeImagesWithCheckedUseCase: GetHomeImagesWithCheckedUseCase,
    private val imageRepository: ImageRepository,
): BaseViewModel() {

    private val _uiState = MutableStateFlow<List<ItemImageUiState>>(emptyList())
    val uiState = _uiState.asStateFlow()

    // TODO: job이 너무 많은건아닌지?
    var searchJob: Job? = null
    var saveJob: Job? = null
    var deleteJob: Job? = null
    var reflectJob: Job? = null

    fun search(keyWord: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            getHomeImagesWithCheckedUseCase(keyWord = keyWord)
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

    // TODO: 일단 save와 delete를 따로 만들고, 가능하면 중복 정리하기
    fun saveImageToLocal(imageUiState: ItemImageUiState) {
        saveJob?.cancel()
        saveJob = viewModelScope.launch {
            imageRepository.saveImageToLocal(imageUiState)
                .onStart { setLoading(true) }
                .flowOn(Dispatchers.IO)
                .map { Result.success(it) }
                .catch { emit(Result.failure(it)) }
                .onCompletion { setLoading(false) }
                .collect { result ->
                    result.fold(
                        onSuccess = { savedImage ->
                            _uiState.update { uiState ->
                                val updateTargetImageIndex = uiState.indexOf(savedImage.copy(isFavorite = false))
                                uiState.toMutableList().apply {
                                    set(updateTargetImageIndex, savedImage)
                                }
                            }
                        },
                        onFailure = (::showError)
                    )
                }
        }
    }

    // TODO: 공통 중간 연산자코드 정리
    fun deleteImageToLocal(imageUiState: ItemImageUiState) {
        deleteJob?.cancel()
        deleteJob = viewModelScope.launch {
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
                                val updateTargetImageIndex = uiState.indexOf(deletedItem)
                                uiState.toMutableList().apply {
                                    set(updateTargetImageIndex, deletedItem.copy(isFavorite = false))
                                }
                            }
                        },
                        onFailure = (::showError)
                    )
                }
        }
    }
}