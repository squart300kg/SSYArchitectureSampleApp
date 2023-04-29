package com.example.architecture.ui.ui.locker

import androidx.lifecycle.viewModelScope
import com.example.architecture.data.repository.SearchResultRepository
import com.example.architecture.ui.base.BaseViewModel
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