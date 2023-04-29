package com.example.architecture.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

open class BaseActivity<T: ViewDataBinding>(
    @LayoutRes private val layoutRes: Int
): AppCompatActivity() {

    protected lateinit var dataBinding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, layoutRes)
        dataBinding.lifecycleOwner = this
    }

    protected fun binding(action: T.() -> Unit) {
        dataBinding.run(action)
    }

}