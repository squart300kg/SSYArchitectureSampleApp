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

@BindingAdapter("loadingStateFromAdt")
fun ProgressBar.loadProgressBar(
    loadStateFromAdt: Flow<CombinedLoadStates>,
) {
    repeatOnResumeLifecycle {
        launch {
            loadStateFromAdt.collectLatest { loadStates ->
                isVisible = loadStates.refresh is LoadState.Loading
            }
        }
    }
}
