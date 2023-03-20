package com.example.kakao.uilayer.ui.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kakao.di.IoDispatcher
import com.example.kakao.domainlayer.GetSortedHomeImageUseCase
import com.example.kakao.uilayer.model.ItemImageUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.FlowPreview
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
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _searchResultUiState = MutableStateFlow(SearchResultUiState())
    val searchResultUiState = _searchResultUiState.asStateFlow()

    // TODO: 연속 검색을 방지하기 위한 debounce?
    // TODO: 동일 검색 결과를 방지하기 위한 disticntUntilChanged?
    @OptIn(FlowPreview::class)
    fun search(keyWord: String) {
        Log.i("key", keyWord)
        viewModelScope.launch {
            getSortedHomeImageUseCase(keyWord = keyWord)
                .flowOn(ioDispatcher)
                .catch {  }
                .collect { itemImageUiState ->
                    _searchResultUiState.update {
                        it.copy(items = itemImageUiState)
                    }
                }


        }
    }

}