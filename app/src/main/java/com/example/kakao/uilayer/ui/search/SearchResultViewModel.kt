package com.example.kakao.uilayer.ui.search

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kakao.domainlayer.GetSortedHomeImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class SearchResultViewModel @Inject constructor(
    private val getSortedHomeImageUseCase: GetSortedHomeImageUseCase
) : ViewModel() {

    // TODO: 연속 검색을 방지하기 위한 debounce?
    // TODO: 동일 검색 결과를 방지하기 위한 disticntUntilChanged?
    fun search(keyWord: String) {
        Log.i("key", keyWord)
        viewModelScope.launch {
            getSortedHomeImageUseCase()
                .flowOn(Dispatchers.IO)
                .catch {  }
                .collect {

                }


        }
    }

}