package com.example.kakao.uilayer.ui.search

import androidx.lifecycle.viewModelScope
import com.example.kakao.datalayer.repository.ImageRepository
import com.example.kakao.domainlayer.GetHomeImagesWithCheckedUseCase
import com.example.kakao.uilayer.base.BaseViewModel
import com.example.kakao.uilayer.model.ItemImageUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SearchResultUiState(
    val items: List<ItemImageUiState> = emptyList()
)

@HiltViewModel
class SearchResultViewModel @Inject constructor(
    private val getHomeImagesWithCheckedUseCase: GetHomeImagesWithCheckedUseCase,
    private val imageRepository: ImageRepository,
): BaseViewModel() {

    private val _searchResultUiState = MutableStateFlow(SearchResultUiState())
    val searchResultUiState = _searchResultUiState.asStateFlow()

    fun search(keyWord: String) {
        viewModelScope.launch {
            getHomeImagesWithCheckedUseCase(keyWord = keyWord)
                .onStart { setLoading(true) }
                .flowOn(Dispatchers.IO)
                .map { Result.success(it) }
                .catch { emit(Result.failure(it)) }
                .onCompletion { setLoading(false) }
                .collect { result ->
                    result.fold(
                        onSuccess = { uiStates ->
                            _searchResultUiState.update {
                                it.copy(items = uiStates)
                            }
                        },
                        onFailure = (::showError)
                    )
                }
        }
    }

    // TODO: 일단 save와 delete를 따로 만들고, 가능하면 중복 정리하기
    fun saveImageToLocal(imageUiState: ItemImageUiState) {
        viewModelScope.launch {
            imageRepository.saveImageToLocal(imageUiState)
                .onStart { setLoading(true) }
                .flowOn(Dispatchers.IO)
                .map { Result.success(it) }
                .catch { emit(Result.failure(it)) }
                .onCompletion { setLoading(false) }
                .collect { result ->
                    result.fold(
                        onSuccess = { },
                        onFailure = (::showError)
                    )
                }
        }
    }

    // TODO: 공통 중간 연산자코드 정리
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
                        onSuccess = { },
                        onFailure = (::showError)
                    )
                }
        }
    }

}