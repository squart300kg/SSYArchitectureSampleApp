package com.example.kakao.ui.ext

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.PagingDataAdapter
import com.example.kakao.ui.adapter.SearchResultAdapter
import com.example.kakao.ui.adapter.isItemNonZero
import com.example.kakao.ui.adapter.isItemZero
import com.example.kakao.ui.model.SearchResultItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@BindingAdapter("setPagingDataVisibility", "isPagingDataVisibilityReverse", requireAll = false)
fun View.setPagingDataVisibility(
    pagingAdapter: PagingDataAdapter<SearchResultItem, SearchResultAdapter.SearchResultViewHolder>,
    isPagingDataVisibilityReverse: Boolean = false,
) {
    setDataVisibility(
        observingTarget = pagingAdapter.loadStateFlow,
        isDataReverse = isPagingDataVisibilityReverse,
        reverseCondition = pagingAdapter.itemCount::isItemZero,
        nonReverseCondition = pagingAdapter.itemCount::isItemNonZero,
    )
}

@BindingAdapter("setListDataVisibility", "isListDataVisibilityReverse", requireAll = false)
fun View.setListDataVisibility(
    listDataFlow: StateFlow<List<SearchResultItem>>,
    isListDataVisibilityReverse: Boolean = false,
) {
    setDataVisibility(
        observingTarget = listDataFlow,
        isDataReverse = isListDataVisibilityReverse,
        reverseCondition = listDataFlow.value::isEmpty,
        nonReverseCondition = listDataFlow.value::isNotEmpty,
    )
}

fun <T: Flow<B>, B> View.setDataVisibility(
    observingTarget: T,
    isDataReverse: Boolean,
    reverseCondition: () -> Boolean,
    nonReverseCondition: () -> Boolean,
) {
    repeatOnResumeLifecycle {
        launch {
            observingTarget.collectLatest {
                isVisible = if (isDataReverse) {
                    reverseCondition()
                } else {
                    nonReverseCondition()
                }
            }
        }
    }
}

fun View.repeatOnResumeLifecycle(block: suspend CoroutineScope.() -> Unit) {
    findViewTreeLifecycleOwner()?.run {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                block()
            }
        }
    }
}