package com.example.kakao.ui.ext

import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@BindingAdapter("errorMessageFromAdt", "errorMessageFromVm")
fun ConstraintLayout.showErrorMessage(
    adtMessage: Flow<CombinedLoadStates>,
    vmMessage: StateFlow<String?>,
) {
    repeatOnResumeLifecycle {
        launch {
            adtMessage.collectLatest { loadStates ->
                if (loadStates.refresh is LoadState.Error) {
                    Toast.makeText(
                        context,
                        "${((loadStates.refresh as LoadState.Error).error.message)}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
        launch {
            vmMessage.collectLatest { errorMessage ->
                errorMessage?.let { message ->
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}