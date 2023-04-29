package com.example.architecture.ui.ext

import androidx.databinding.BindingAdapter
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import com.example.architecture.ui.adapter.SearchResultAdapter
import com.example.architecture.ui.model.SearchResultItem
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest

@BindingAdapter("submitPagingData")
fun RecyclerView.submitPagingData(uiStateFlow: StateFlow<PagingData<SearchResultItem>>) {
    submitData(
        data = uiStateFlow,
        pagingDataTransition = { it }
    )
}

@BindingAdapter("submitListData")
fun RecyclerView.submitListData(uiStateFlow: StateFlow<List<SearchResultItem>>) {
    submitData(
        data = uiStateFlow,
        pagingDataTransition = { PagingData.from(it) }
    )
}

fun <T> RecyclerView.submitData(
    data: StateFlow<T>,
    pagingDataTransition: (T) -> PagingData<SearchResultItem>
) {
    repeatOnResumeLifecycle {
        data.collectLatest {
            (adapter as SearchResultAdapter).submitData(pagingDataTransition(it))
        }
    }
}

