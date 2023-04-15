package com.example.kakao.uilayer.ext

import androidx.databinding.BindingAdapter
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import com.example.kakao.datalayer.model.response.ModifySuccessModel
import com.example.kakao.uilayer.adapter.ImageAdapter
import com.example.kakao.uilayer.model.ItemImageUiState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest


@BindingAdapter("submitImageData")
fun RecyclerView.submitImageData(uiStateFlow: StateFlow<PagingData<ItemImageUiState>>) {
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

