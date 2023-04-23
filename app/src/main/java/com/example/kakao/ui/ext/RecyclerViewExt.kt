package com.example.kakao.ui.ext

import androidx.databinding.BindingAdapter
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import com.example.kakao.ui.adapter.SearchResultAdapter
import com.example.kakao.ui.model.SearchResultItem
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest


@BindingAdapter("submitImageData")
fun RecyclerView.submitImageData(uiStateFlow: StateFlow<PagingData<SearchResultItem>>) {
    repeatOnResumeLifecycle {
        uiStateFlow.collectLatest {
            val adapter = adapter as SearchResultAdapter
            adapter.submitData(it)
        }
    }
}
