package com.example.kakao.uilayer.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.kakao.util.GlideUtil

@BindingAdapter("setImage")
fun ImageView.setImage(url: String?) {
    url?.let { url ->
        GlideUtil.loadImage(this, url)
    }
}