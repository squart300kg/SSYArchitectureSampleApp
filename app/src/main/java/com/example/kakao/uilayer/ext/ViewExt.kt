package com.example.kakao.uilayer.ext

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun View.repeatOnResumeLifecycle(block: suspend CoroutineScope.() -> Unit) {
    findViewTreeLifecycleOwner()?.run {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                block()
            }
        }
    }
}