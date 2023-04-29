package com.example.kakao.ui.ui.locker

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.kakao.data.repository.SearchResultRepository
import com.example.kakao.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MyLockerViewModel @Inject constructor(
    searchResultRepository: SearchResultRepository,
) : BaseViewModel() {

    val uiState = searchResultRepository.localSearchResultModels
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )
}