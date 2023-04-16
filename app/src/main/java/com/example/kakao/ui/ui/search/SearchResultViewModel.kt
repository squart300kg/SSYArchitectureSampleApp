package com.example.kakao.ui.ui.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.kakao.data.model.response.ModifySuccessModel
import com.example.kakao.data.repository.ImageRepository
import com.example.kakao.domain.GetHomeItemsWithCheckedUseCase
import com.example.kakao.ui.base.BaseViewModel
import com.example.kakao.ui.model.Result2
import com.example.kakao.ui.model.SearchResultItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

const val SEARCH_KEY_WORD_KEY = "SEARCH_KEY_WORD"

@HiltViewModel
class SearchResultViewModel @Inject constructor(
    private val getHomeItemsWithCheckedUseCase: GetHomeItemsWithCheckedUseCase,
    private val imageRepository: ImageRepository,
    private val savedStateHandle: SavedStateHandle
): BaseViewModel() {

    private val _searchKeyWord = savedStateHandle.getStateFlow(SEARCH_KEY_WORD_KEY, "")
    val uiState = _searchKeyWord
        .flatMapLatest { getHomeItemsWithCheckedUseCase(it) }
        .cachedIn(viewModelScope)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = PagingData.empty()
        )

    private val _modifyingUiState = MutableStateFlow<Pair<Int, ModifySuccessModel>?>(null)
    val modifyingUiState = _modifyingUiState.asStateFlow()

    var saveJob: Job? = null
    var deleteJob: Job? = null

    fun search(keyWord: String) {
        savedStateHandle[SEARCH_KEY_WORD_KEY] = keyWord
    }

    fun saveImageToLocal(imageUiState: SearchResultItem, modifyingTargetIndex: Int) {
        saveJob?.cancel()
        saveJob = viewModelScope.launch {
            imageRepository.saveImageToLocal(imageUiState)
                .setBaseIntermediates()
                .collect { result ->
                    result.fold(
                        onSuccess = { modifySuccessModel ->
                            _modifyingUiState.update { modifyingTargetIndex to modifySuccessModel }
                            _modifyingUiState.update { null }
                        },
                        onFailure = (::showError)
                    )
                }
        }
    }

     fun deleteImageToLocal(imageUiState: SearchResultItem, modifyingTargetIndex: Int) {
         deleteJob?.cancel()
         deleteJob = viewModelScope.launch {
             imageRepository.deleteImageToLocal(imageUiState)
                 .setBaseIntermediates()
                 .collect { result ->
                     result.fold(
                         onSuccess = { modifySuccessModel ->
                             _modifyingUiState.update { modifyingTargetIndex to modifySuccessModel }
                             _modifyingUiState.update { null }
                         },
                         onFailure = (::showError)
                     )
                 }
         }
     }
}

