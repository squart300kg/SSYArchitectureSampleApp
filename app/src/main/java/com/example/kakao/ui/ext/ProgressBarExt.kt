package com.example.kakao.ui.ext

import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@BindingAdapter("loadingStateFromAdt", "loadingStateFromVm")
fun ProgressBar.loadProgressBar(
    loadStateFromAdt: Flow<CombinedLoadStates>,
    loadStateFromVm: StateFlow<Boolean>
) {
    repeatOnResumeLifecycle {
        launch {
            loadStateFromAdt.collectLatest { loadStates ->
                isVisible = loadStates.refresh is LoadState.Loading
            }
        }
        launch {
            loadStateFromVm.collectLatest { loadState ->
                isVisible = loadState
            }
        }

    }
}
