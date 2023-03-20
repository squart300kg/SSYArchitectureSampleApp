package com.example.kakao.uilayer.adapter

import android.util.Log
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.kakao.databinding.ItemImageBinding
import com.example.kakao.uilayer.base.BaseViewHolder
import com.example.kakao.BR
import com.example.kakao.R
import com.example.kakao.uilayer.model.ItemImageUiState

class ImageAdapter: RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    private val items = mutableListOf<ItemImageUiState>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImageViewHolder {
        return ImageViewHolder(
            BR.imageItem,
            parent,
            R.layout.item_image
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount() = items.size

    fun submitList(list: List<ItemImageUiState>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    inner class ImageViewHolder(
        itemId: Int,
        parent: ViewGroup,
        layoutRes: Int
    ): BaseViewHolder<ItemImageUiState, ItemImageBinding>(itemId, parent, layoutRes) {

    }
}