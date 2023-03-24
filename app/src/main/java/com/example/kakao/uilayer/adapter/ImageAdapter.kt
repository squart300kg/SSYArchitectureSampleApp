package com.example.kakao.uilayer.adapter

import android.util.Log
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kakao.BR
import com.example.kakao.R
import com.example.kakao.databinding.ItemImageBinding
import com.example.kakao.uilayer.base.BaseViewHolder
import com.example.kakao.uilayer.model.ItemImageUiState

enum class ImageAdapterType {
    SEARCH_RESULT,
    MY_LOCKER
}

class ImageAdapter(
    private val imageAdapterType: ImageAdapterType,
    private val onSaveImage: (ItemImageUiState) -> Unit = {},
    private val onDeleteImage: (ItemImageUiState) -> Unit = {},
): PagingDataAdapter<ItemImageUiState, ImageAdapter.ImageViewHolder>(ImageComparator){

    object ImageComparator : DiffUtil.ItemCallback<ItemImageUiState>() {
        override fun areItemsTheSame(oldItem: ItemImageUiState, newItem: ItemImageUiState)
                = oldItem.thumbnailUrl == newItem.thumbnailUrl

        override fun areContentsTheSame(oldItem: ItemImageUiState, newItem: ItemImageUiState)
                = oldItem == newItem
    }

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
        holder.bindItem(getItem(position) ?: ItemImageUiState())
        holder.initClickListener()
    }

    inner class ImageViewHolder(
        itemId: Int,
        parent: ViewGroup,
        layoutRes: Int
    ): BaseViewHolder<ItemImageUiState, ItemImageBinding>(itemId, parent, layoutRes) {

        init {
            if (imageAdapterType == ImageAdapterType.MY_LOCKER) {
                binding {
                    checkBox.isVisible = false
                }
            }
        }

        fun initClickListener() {
            binding {
                checkBox.setOnClickListener {

                    val modifyingTargetItem = snapshot()[absoluteAdapterPosition]?.copy(isFavorite = true) ?: ItemImageUiState()
                    snapshot()[absoluteAdapterPosition]?.isFavorite = checkBox.isChecked
                    if (checkBox.isChecked) {
                        onSaveImage(modifyingTargetItem)
                    } else {
                        onDeleteImage(modifyingTargetItem)
                    }

                }
            }
        }
    }
}