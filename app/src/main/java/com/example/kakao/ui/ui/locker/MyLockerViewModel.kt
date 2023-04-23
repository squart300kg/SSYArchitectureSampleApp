package com.example.kakao.ui.ui.locker

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.kakao.data.repository.SearchResultRepository
import com.example.kakao.ui.base.BaseViewModel
import com.example.kakao.ui.model.SearchResultItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyLockerViewModel @Inject constructor(
    private val searchResultRepository: SearchResultRepository,
) : BaseViewModel() {

    val uiState = searchResultRepository.localSearchResultModels
        .map { PagingData.from(it) }
        .cachedIn(viewModelScope)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(1000L),
            initialValue = PagingData.empty()

        )
}