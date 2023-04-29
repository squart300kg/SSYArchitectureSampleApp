package com.example.architecture.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.architecture.R

object GlideUtil {
    fun loadImage(imageView: ImageView, url: String) {
        val radius = 20.0f
        Glide.with(imageView)
            .load(url)
            .apply(
                RequestOptions.bitmapTransform(
                    MultiTransformation(
                        CenterCrop(),
                        GranularRoundedCorners(radius, radius, radius, radius)
                    )
                )
            )
            .placeholder(R.color.black)
            .error(R.color.black)
            .into(imageView)
    }
}