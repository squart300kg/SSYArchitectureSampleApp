package com.example.kakao.uilayer.ui.locker

import androidx.lifecycle.viewModelScope
import com.example.kakao.datalayer.repository.ImageRepository
import com.example.kakao.uilayer.base.BaseViewModel
import com.example.kakao.uilayer.model.ItemImageUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyLockerViewModel @Inject constructor(
    private val imageRepository: ImageRepository,
) : BaseViewModel() {

    val uiState = imageRepository.localImages()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )
}