package com.example.kakao.ui.ui.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.kakao.data.model.response.ModifySuccessModel
import com.example.kakao.data.repository.SearchResultRepository
import com.example.kakao.domain.GetHomeItemsWithCheckedUseCase
import com.example.kakao.ui.base.BaseViewModel
import com.example.kakao.ui.model.SearchResultItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

const val SEARCH_KEY_WORD_KEY = "SEARCH_KEY_WORD"

@HiltViewModel
class SearchResultViewModel @Inject constructor(
    private val getSearchResultItemsWithChecked: GetHomeItemsWithCheckedUseCase,
    private val searchResultRepository: SearchResultRepository,
    private val savedStateHandle: SavedStateHandle
): BaseViewModel() {

    private val _searchKeyWord = savedStateHandle.getStateFlow(SEARCH_KEY_WORD_KEY, "")
    private val _updateTriggered = MutableStateFlow(false)
    val uiState = _searchKeyWord
        .flatMapLatest { getSearchResultItemsWithChecked(it) }
        .cachedIn(viewModelScope)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = PagingData.empty()
        )

    private val _modifyingUiState = MutableStateFlow<Pair<Int, ModifySuccessModel>?>(null)
    val modifyingUiState = _modifyingUiState.asStateFlow()

    fun search(keyWord: String) {
        savedStateHandle[SEARCH_KEY_WORD_KEY] = keyWord
    }

    fun saveSearchResultToLocal(searchResultItem: SearchResultItem) {
        viewModelScope.launch {
            searchResultRepository.saveSearchResultModel(searchResultItem)
            _updateTriggered.value = true
        }
    }

     fun deleteSearchResultToLocal(searchResultItem: SearchResultItem) {
         viewModelScope.launch {
             searchResultRepository.deleteSearchResultModels(searchResultItem)
             _updateTriggered.value = true
         }
     }
}

