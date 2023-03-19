package com.example.kakao.uilayer.ui.search

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.kakao.domainlayer.GetSortedHomeImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject



@HiltViewModel
class SearchResultViewModel @Inject constructor(
    getSortedHomeImageUseCase: GetSortedHomeImageUseCase
) : ViewModel() {

    fun search(keyWord: String) {
        Log.i("key", keyWord)
    }

}