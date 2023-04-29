package com.example.kakao.ui.adapter

import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.kakao.BR
import com.example.kakao.R
import com.example.kakao.databinding.ItemSearchResultBinding
import com.example.kakao.ui.base.BaseViewHolder
import com.example.kakao.ui.model.SearchResultItem

enum class SearchResultAdapterType {
    SEARCH_RESULT,
    MY_LOCKER
}

class SearchResultAdapter(
    private val searchResultAdapterType: SearchResultAdapterType,
    private val onUpdateSearchResultModelToLocal: (SearchResultItem) -> Unit = {},
) : PagingDataAdapter<SearchResultItem, SearchResultAdapter.SearchResultViewHolder>(
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
    ): SearchResultViewHolder {
        return SearchResultViewHolder(
            BR.searchItem,
            parent,
            R.layout.item_search_result
        )
    }


    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.bindItem(getItem(position) ?: SearchResultItem())
        holder.initClickListener()
    }

    inner class SearchResultViewHolder(
        itemId: Int,
        parent: ViewGroup,
        layoutRes: Int
    ) : BaseViewHolder<SearchResultItem, ItemSearchResultBinding>(
        itemId,
        parent,
        layoutRes
    ) {

        init {
            if (searchResultAdapterType == SearchResultAdapterType.MY_LOCKER) {
                binding {
                    checkBox.isVisible = false
                }
            }
        }

        fun initClickListener() {
            binding {
                checkBox.setOnClickListener {

                    getItem(absoluteAdapterPosition)?.let { modifyingTargetItem ->
                        snapshot()[absoluteAdapterPosition]?.isFavorite = checkBox.isChecked
                        onUpdateSearchResultModelToLocal(modifyingTargetItem)
                    }
                }
            }
        }
    }
}