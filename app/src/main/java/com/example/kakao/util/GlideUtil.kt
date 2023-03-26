package com.example.kakao.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.kakao.R

object GlideUtil {
    fun loadImage(imageView: ImageView, url: String) {
        Glide.with(imageView)
            .load(url)
            .apply(
                RequestOptions.bitmapTransform(
                    MultiTransformation(
                        CenterCrop(),
                    )
                )
            )
            .placeholder(R.color.black)
            .error(R.color.black)
            .into(imageView)
    }
}