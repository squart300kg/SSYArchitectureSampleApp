package com.example.kakao.uilayer.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kakao.domainlayer.GetSortedHomeImageUseCase
import com.example.kakao.uilayer.model.ItemImageUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

// TODO: 매우 중요!!! 카카오 키해시 등록 어떻게할지 고민

data class SearchResultUiState(
    val items: List<ItemImageUiState> = emptyList()
)

@HiltViewModel
class SearchResultViewModel @Inject constructor(
    private val getSortedHomeImageUseCase: GetSortedHomeImageUseCase,
) : ViewModel() {

    private val _searchResultUiState = MutableStateFlow(SearchResultUiState())
    val searchResultUiState = _searchResultUiState.asStateFlow()

    fun search(keyWord: String) {
        viewModelScope.launch {
            getSortedHomeImageUseCase(keyWord = keyWord)
                .flowOn(Dispatchers.IO)
                .catch {  }
                .collect { itemImageUiState ->
                    _searchResultUiState.update {
                        it.copy(items = itemImageUiState)
                    }
                }
        }
    }

}