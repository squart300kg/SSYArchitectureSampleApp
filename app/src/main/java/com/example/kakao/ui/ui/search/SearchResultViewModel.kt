package com.example.kakao.ui.ui.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.kakao.data.repository.SearchResultRepository
import com.example.kakao.domain.GetHomeItemsWithCheckedUseCase
import com.example.kakao.ui.base.BaseViewModel
import com.example.kakao.ui.model.SearchResultItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

const val SEARCH_KEY_WORD_KEY = "SEARCH_KEY_WORD"

@HiltViewModel
class SearchResultViewModel @Inject constructor(
    private val getSearchResultItemsWithChecked: GetHomeItemsWithCheckedUseCase,
    private val searchResultRepository: SearchResultRepository,
    private val savedStateHandle: SavedStateHandle
): BaseViewModel() {

    private val _searchKeyWord = savedStateHandle.getStateFlow(SEARCH_KEY_WORD_KEY, "jbj김상균")
    val uiState = _searchKeyWord
        .filter { it.isNotEmpty() }
        .flatMapLatest { getSearchResultItemsWithChecked(it) }
        .cachedIn(viewModelScope)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = PagingData.empty()
        )

    fun search(keyWord: String) {
        savedStateHandle[SEARCH_KEY_WORD_KEY] = keyWord
    }

    fun updateSearchResultToLocal(searchResultItem: SearchResultItem) =
        searchResultRepository.updateSearchResultToLocal(searchResultItem)

}

