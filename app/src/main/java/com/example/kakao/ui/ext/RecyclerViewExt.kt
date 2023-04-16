package com.example.kakao.ui.ext

import androidx.databinding.BindingAdapter
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import com.example.kakao.data.model.response.ModifySuccessModel
import com.example.kakao.ui.adapter.ImageAdapter
import com.example.kakao.ui.model.SearchResultItem
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest


@BindingAdapter("submitImageData")
fun RecyclerView.submitImageData(uiStateFlow: StateFlow<PagingData<SearchResultItem>>) {
    repeatOnResumeLifecycle {
        uiStateFlow.collectLatest {
            (adapter as ImageAdapter).submitData(it)
        }
    }
}

@BindingAdapter("modifyItem")
fun RecyclerView.modifyItem(uiStateFlow: StateFlow<Pair<Int, ModifySuccessModel>?>) {
    repeatOnResumeLifecycle {
        uiStateFlow.collectLatest { modifyingUiState ->
            modifyingUiState?.let {
                (adapter as ImageAdapter).updateItem(
                    modifyingTargetIndex = it.first,
                    modifySuccessModel = it.second
                )
            }
        }
    }
}

