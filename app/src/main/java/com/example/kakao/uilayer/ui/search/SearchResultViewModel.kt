package com.example.kakao.uilayer.ui.search

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.kakao.datalayer.model.response.ModifySuccessModel
import com.example.kakao.datalayer.repository.ImageRepository
import com.example.kakao.domainlayer.GetHomeItemsWithCheckedUseCase
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
    private val getHomeItemsWithCheckedUseCase: GetHomeItemsWithCheckedUseCase,
    private val imageRepository: ImageRepository,
): BaseViewModel() {

    private val _homeItemUiState = MutableStateFlow<PagingData<ItemImageUiState>>(PagingData.empty())
    val homeItemUiState = _homeItemUiState.asStateFlow()

    private val _modifyingUiState = MutableStateFlow<Pair<Int, ModifySuccessModel>?>(null)
    val modifyingUiState = _modifyingUiState.asStateFlow()

    var searchJob: Job? = null
    var saveJob: Job? = null
    var deleteJob: Job? = null

    fun search(keyWord: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            getHomeItemsWithCheckedUseCase(keyWord = keyWord)
                .cachedIn(viewModelScope)
                .flowOn(Dispatchers.IO)
                .collect { receivedImages ->
                    _homeItemUiState.update { receivedImages }
                }
        }
    }

    fun saveImageToLocal(imageUiState: ItemImageUiState, modifyingTargetIndex: Int) {
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

     fun deleteImageToLocal(imageUiState: ItemImageUiState, modifyingTargetIndex: Int) {
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