package com.example.kakao.ui.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.kakao.util.GlideUtil

@BindingAdapter("setImage")
fun ImageView.setImage(url: String?) {
    url?.let { url ->
        GlideUtil.loadImage(this, url)
    }
}