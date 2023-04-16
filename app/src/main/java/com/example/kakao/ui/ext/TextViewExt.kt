package com.example.kakao.ui.ext

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("setText")
fun TextView.setText(inputText: String?) {
    inputText?.let { inputText ->
        this.text = inputText
    }
}