package com.example.kakao.uilayer.adapter

import android.util.Log
import android.view.ViewGroup
import androidx.core.view.isVisible
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
): RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

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
        holder.initClickListener()
    }

    override fun getItemCount() = items.size

    fun submitItems(list: List<ItemImageUiState>) {
        Log.i("updateTest", "adapter submit : ")

        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    fun updateItem(targetImage: ItemImageUiState) {
        val updateTargetImage = items.find { ownImage -> ownImage.thumbnailUrl == targetImage.thumbnailUrl }
        val updateTargetIndex = items.indexOf(updateTargetImage)
        items[updateTargetIndex] = targetImage
//        notifyItemChanged(updateTargetIndex)
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
                    if (checkBox.isChecked) {
                        Log.i("updateTest", "in adapter save position : "+absoluteAdapterPosition.toString())

                        onSaveImage(items[absoluteAdapterPosition].copy(isFavorite = true))
                        items[absoluteAdapterPosition] = items[absoluteAdapterPosition].copy(isFavorite = true)
                    } else {
                        Log.i("updateTest", "in adapter dele position : "+absoluteAdapterPosition.toString())

                        onDeleteImage(items[absoluteAdapterPosition])
                        items[absoluteAdapterPosition] = items[absoluteAdapterPosition].copy(isFavorite = false)
                    }
                }
            }
        }
    }
}