package com.example.architecture.ui.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.architecture.util.GlideUtil

@BindingAdapter("setImage")
fun ImageView.setImage(url: String?) {
    url?.let { url ->
        GlideUtil.loadImage(this, url)
    }
}