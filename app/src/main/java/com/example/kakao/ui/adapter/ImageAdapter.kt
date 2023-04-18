package com.example.kakao.ui.adapter

import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.kakao.BR
import com.example.kakao.R
import com.example.kakao.databinding.ItemImageBinding
import com.example.kakao.data.model.response.ModifySuccessModel
import com.example.kakao.ui.base.BaseViewHolder
import com.example.kakao.ui.model.SearchResultItem

enum class ImageAdapterType {
    SEARCH_RESULT,
    MY_LOCKER
}

class ImageAdapter(
    private val imageAdapterType: ImageAdapterType,
    private val onSaveImage: (SearchResultItem) -> Unit = {  },
    private val onDeleteImage: (SearchResultItem) -> Unit = {  },
) : PagingDataAdapter<SearchResultItem, ImageAdapter.ImageViewHolder>(
    ImageComparator
) {

    object ImageComparator : DiffUtil.ItemCallback<SearchResultItem>() {
        override fun areItemsTheSame(
            oldItem: SearchResultItem,
            newItem: SearchResultItem
        ) = oldItem.thumbnailUrl == newItem.thumbnailUrl

        override fun areContentsTheSame(
            oldItem: SearchResultItem,
            newItem: SearchResultItem
        ) = oldItem == newItem
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
        holder.bindItem(getItem(position) ?: SearchResultItem())
        holder.initClickListener()
    }

    fun updateItem(modifyingTargetIndex: Int, modifySuccessModel: ModifySuccessModel) {
        getItem(modifyingTargetIndex)?.isFavorite = modifySuccessModel.isFavorite
    }

    inner class ImageViewHolder(
        itemId: Int,
        parent: ViewGroup,
        layoutRes: Int
    ) : BaseViewHolder<SearchResultItem, ItemImageBinding>(
        itemId,
        parent,
        layoutRes
    ) {

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

                    getItem(absoluteAdapterPosition)?.let { modifyingTargetItem ->
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
}