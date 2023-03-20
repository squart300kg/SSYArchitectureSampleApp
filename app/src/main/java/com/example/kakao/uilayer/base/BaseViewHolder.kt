package com.example.kakao.uilayer.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder<T, B: ViewDataBinding>(
    private val itemId: Int,
    parent: ViewGroup,
    @LayoutRes layoutRes: Int,
): RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)) {
    private val itemBinding: B? = DataBindingUtil.bind(itemView)

    fun bindItem(item: T) {
        itemBinding?.setVariable(itemId, item)
        itemBinding?.executePendingBindings()
    }

    protected fun binding(action: B?.() -> Unit) {
        itemBinding.run(action)
    }


}
